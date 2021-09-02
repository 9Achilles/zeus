package zeus.job;

import org.springframework.stereotype.Service;
import zeus.dispatch.DispatchEngine;
import zeus.entry.entity.ZeusTaskDO;
import zeus.mapper.ZeusExecutorMapper;
import zeus.mapper.ZeusJobTaskInfoMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ScheduleJobService {

    @Resource
    private DispatchEngine dispatchEngine;

    @PostConstruct
    public void InitJob() {
        //查所有的job，初始化到定时队列
        List<ZeusTaskDO> scheduleJobDOS = new ArrayList<>();

        //all job id

        for(ZeusTaskDO scheduleJobDO : scheduleJobDOS){

            Long id = null;
            dispatchEngine.addTask(id);
        }
    }





}
