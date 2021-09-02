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
import java.util.Optional;
import java.util.concurrent.*;

@Component
public class ScheduleExecuteDispatchEngine implements DispatchEngine {

    @Autowired
    @Qualifier("HttpClientRPCService")
    private RPCService rpcService;

    private final Map<String, RunnableScheduledFuture> futures = new ConcurrentHashMap<>();

    //定时任务队列
    private final static ScheduledThreadPoolExecutor scheduledDelayExecutor = new ScheduledThreadPoolExecutor(10);

    //添加调度任务
    @Override
    public boolean addTask(Long jobId) {
        try {
            long start = 0;
            long period = 0;
            TimeUnit unit = null;
            //任务入队
            submitTask(String.valueOf(jobId), () -> {
                boolean check = ZKLockUtil.lock(String.valueOf(jobId));
                if (check) {
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
    public boolean deleteTask(Long jobId) {
        //delete task
        if (Optional.ofNullable(jobId).isPresent()) {
            try {
                RunnableScheduledFuture future = futures.get(String.valueOf(jobId));
                if (Optional.ofNullable(future).isPresent() && future.isCancelled()) {
                    scheduledDelayExecutor.remove(future);
                    futures.remove(jobId);
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean stopTask(Long jobId) {
        return deleteTask(jobId);
    }

    @Override
    public boolean reStartTask(Long jobId) {
        addTask(jobId);
        return false;
    }


    private boolean submitTask(String jobId, Runnable command, long start, long period, TimeUnit unit) {
        try {

            RunnableScheduledFuture scheduledFuture = (RunnableScheduledFuture) scheduledDelayExecutor.scheduleAtFixedRate(command, start, period, unit);
            //内存
            futures.put(String.valueOf(jobId), scheduledFuture);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
