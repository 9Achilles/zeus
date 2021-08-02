package zeus.dispatch;

//任务配置  ---  任务提交（触发执行）  ---  任务进队   ---   任务执行  ---  任务回显记录
//
public interface DispatchEngine {


    boolean executeDelayTask(String taskId);

    boolean executeTask(String taskId);


}
