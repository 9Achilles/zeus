package zeus.dispatch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import zeus.dispatch.DispatchEngine;
import zeus.entry.dto.TaskDTO;
import zeus.rpc.RPCService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecuteDispatchEngine implements DispatchEngine {

    @Autowired
    @Qualifier("HttpClientRPCService")
    private RPCService rpcService;

    //定时任务队列
    private final static ScheduledExecutorService scheduledDelayExecutor = Executors.newScheduledThreadPool(10);

    //立刻执行任务队列
    private final static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);


    @Override
    public boolean executeDelayTask(String taskId) {
        try {
            //查询该任务详情 ip port url param
            TaskDTO dto = null;
            String url = "http://127.0.0.1:8080/demo/test";
            //任务计算时间
            long start = 5l;
            long period = 5l;
            //获取时间单位
            TimeUnit unit = null;
            //任务入队
            submitTask(scheduledDelayExecutor,()->{
                rpcService.doPost(url,null,null,null);
            }, start, period, unit);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean executeTask(String taskId) {
        //查询该任务详情 ip port url param
        TaskDTO dto = null;
        //生成任务
        Runnable command = null;

        submitTask(scheduledExecutor,command, 0, 0, TimeUnit.MILLISECONDS);
        return false;
    }

    private boolean submitTask(ScheduledExecutorService scheduledExecutor,Runnable command, long start, long period, TimeUnit unit) {
        try {
            scheduledExecutor.scheduleAtFixedRate(command, start, period, unit);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
