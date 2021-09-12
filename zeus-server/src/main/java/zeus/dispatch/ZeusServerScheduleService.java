package zeus.dispatch;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zeus.config.ThreadPoolConfiguration;
import zeus.entry.dto.ZeusTaskDTO;
import zeus.entry.dto.ZeusTaskRecordDTO;
import zeus.manager.ZeusTaskManager;
import zeus.service.WhileTimerService;
import zeus.service.ZeusServerCuratorService;
import zeus.util.CronExpression;
import zeus.util.DateUtil;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class ZeusServerScheduleService {


    @Resource
    private WhileTimerService whileTimerService;

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Resource
    private ZeusTaskManager zeusTaskManager;

    @Resource
    private ZeusServerCuratorService zeusServerCuratorService;

    //每分钟拉该分钟需要执行的任务  a: 13：01  b:13:  13:11 13:21
    @Scheduled(fixedDelay = 60000)
    public void schedule() {

        Date nextMinute = DateUtil.getTime(Calendar.MINUTE, 1);
        Date currentTime = new Date();

        List<ZeusTaskDTO> zeusTaskDTOS = zeusTaskManager.queryByExecuteTime(currentTime);

        zeusTaskDTOS.stream().parallel().forEach(
                task -> {
                    ThreadPoolConfiguration.dispatchThreadPool.execute(() -> {

                        String path = "".concat(task.getTaskId().toString());
                        if (zeusServerCuratorService.createNode(path)) {
                            String cron = task.getCron();
                            CronExpression cronExpression = null;
                            try {
                                cronExpression = new CronExpression(cron);
                            } catch (ParseException e) {
                                System.out.println("cron:".concat(cron).concat("parse fail.Caused by ").concat(e.getMessage()));
                            }
                            push(task, cronExpression, currentTime, nextMinute);
                            zeusServerCuratorService.deleteNode(path);
                        }
                    });
                }
        );
    }


    //每天凌晨0点执行，拉起即日起过期的任务，调整其执行时间
    @Scheduled(cron = "0 0 0 * * * *")
    public void check() {
        //拿到所有执行时间比当前时间晚的任务(当前机器的list)
        List<ZeusTaskDTO> zeusTaskDTOS = new ArrayList<>();
        Date current = new Date();
        zeusTaskDTOS.stream().parallel()
                .forEach(task -> {
                    ThreadPoolConfiguration.checkThreadPool.execute(() -> {
                        try {
                            String path = "".concat(task.getTaskId().toString());
                            if (zeusServerCuratorService.createNode(path)) {
                                String cron = task.getCron();
                                CronExpression cronExpression = new CronExpression(cron);
                                Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(current);
                                task.setExecuteTime(nextValidTimeAfter);
                                zeusTaskManager.updateTaskById(task);
                                zeusServerCuratorService.deleteNode(path);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    });
                });
    }


    private void push(ZeusTaskDTO zeusTaskDTO, CronExpression cronExpression, Date currentTime, Date nextMinute) {

        if (!Optional.ofNullable(cronExpression).isPresent()) {
            return;
        }

        Date nextExecuteTime = cronExpression.getNextValidTimeAfter(currentTime);

        ZeusTaskRecordDTO recordDTO = buildZeusTaskRecordDTO(zeusTaskDTO);

        while (nextMinute.after(nextExecuteTime)) {
            long delay = (nextExecuteTime.getTime() - currentTime.getTime()) / 1000;
            whileTimerService.push(timeout -> {
                try {
                    //todo 保证一定投递成功 且仅投递一次
                    kafkaTemplate.send("ZEUS_WORKER_SUBSCRIBE_TOPIC", JSON.toJSON(recordDTO));
                } catch (Exception exception) {
                    System.out.println("xx执行失败");
                }

            }, delay, TimeUnit.SECONDS);
            nextExecuteTime = cronExpression.getNextValidTimeAfter(nextExecuteTime);
        }

        //更新执行时间
        zeusTaskDTO.setExecuteTime(nextExecuteTime);
        zeusTaskManager.updateTaskById(zeusTaskDTO);
    }

    private ZeusTaskRecordDTO buildZeusTaskRecordDTO(ZeusTaskDTO zeusTaskDTO) {
        ZeusTaskRecordDTO recordDTO = new ZeusTaskRecordDTO();
        BeanUtils.copyProperties(zeusTaskDTO, recordDTO);
        recordDTO.setStatus(0);
        recordDTO.setRecordId("uuid");
        return recordDTO;
    }



}





