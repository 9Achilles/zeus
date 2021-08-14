package zeus.dispatch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import zeus.dispatch.DispatchEngine;
import zeus.entry.dto.TaskDTO;
import zeus.rpc.RPCService;

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
    private final static ScheduledExecutorService scheduledDelayExecutor = Executors.newScheduledThreadPool(10);



    @Override
    public boolean executeDelayTask(String url, long start, long period, TimeUnit unit,
                                    Map<String, String> headers, Map<String, Object> body, String charset) {
        try {
            //任务入队
            submitTask(scheduledDelayExecutor, () -> {
                rpcService.doPost(url, headers, body, charset);
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
        //rpcService.doPost();
        //submitTask(scheduledExecutor, command, 0, 0, TimeUnit.MILLISECONDS);
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
