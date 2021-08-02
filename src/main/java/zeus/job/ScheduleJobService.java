package zeus.job;

import org.springframework.stereotype.Service;
import zeus.dispatch.DispatchEngine;
import zeus.entry.entity.ScheduleJobDO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleJobService {

    @Resource
    private DispatchEngine dispatchEngine;

    @PostConstruct
    public void InitJob() {
        //查所有的job，初始化到定时队列
        List<ScheduleJobDO> scheduleJobDOS = new ArrayList<>();
        scheduleJobDOS.stream().forEach(job -> dispatchEngine.executeDelayTask());
    }


}
