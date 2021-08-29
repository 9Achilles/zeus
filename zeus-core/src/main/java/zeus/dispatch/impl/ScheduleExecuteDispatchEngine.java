package zeus.dispatch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import zeus.dispatch.DispatchEngine;
import zeus.entry.dto.TaskDTO;
import zeus.lock.zk.ZKLockUtil;
import zeus.rpc.RPCService;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduleExecuteDispatchEngine implements DispatchEngine {

    @Autowired
    @Qualifier("HttpClientRPCService")
    private RPCService rpcService;

    //定时任务队列
    private final static ScheduledExecutorService scheduledDelayExecutor = Executors.newScheduledThreadPool(200);


    //添加调度任务
    @Override
    public boolean addTask(Long jobId) {
        try {
            long start = 0;
            long period = 0;
            TimeUnit unit = null;
            //任务入队
            submitTask(scheduledDelayExecutor, () -> {
                boolean check = ZKLockUtil.lock(String.valueOf(jobId));
                if(check){
                    rpcService.doPost(jobId);
                }
            }, start, period, unit);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    //delete task
    @Override
    public boolean deleteTask(String jobId) {

        //stop task


        //delete task

        return false;
    }

    @Override
    public boolean stopTask(String jobId) {

        //stop task

        return false;
    }

    @Override
    public boolean reStartTask(String jobId) {

        // re start job

        return false;
    }


    private boolean submitTask(ScheduledExecutorService scheduledExecutor, Runnable command, long start, long period, TimeUnit unit) {
        try {
            scheduledExecutor.scheduleAtFixedRate(command, start, period, unit);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
