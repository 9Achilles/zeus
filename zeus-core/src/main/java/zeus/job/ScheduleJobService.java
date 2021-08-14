package zeus.job;

import org.springframework.stereotype.Service;
import zeus.dispatch.DispatchEngine;
import zeus.entry.entity.ScheduleTaskDO;

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
        List<ScheduleTaskDO> scheduleJobDOS = new ArrayList<>();

        for(ScheduleTaskDO scheduleJobDO : scheduleJobDOS){
            String url = null;
            long start = 0;
            long period = 0;
            TimeUnit unit = null;
            Map<String, String> headers= null;
            Map<String, Object > body= null;
            String charset = null;
            dispatchEngine.executeDelayTask(url,start,period,unit,headers,body,charset);
        }
    }





}
