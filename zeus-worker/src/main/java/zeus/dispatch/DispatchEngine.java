package zeus.dispatch;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//任务配置  ---  任务提交（触发执行）  ---  任务进队   ---   任务执行  ---  任务回显记录
//job   rpc任务
//task  定时任务
public interface DispatchEngine {


    boolean addTask(Long jobId);


    boolean deleteTask(Long jobId);


    boolean stopTask(Long jobId);

    boolean reStartTask(Long jobId);


}
