package zeus.dispatch;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//任务配置  ---  任务提交（触发执行）  ---  任务进队   ---   任务执行  ---  任务回显记录
//
public interface DispatchEngine {


    boolean executeDelayTask(String url, long start, long period, TimeUnit unit,
                             Map<String, String> headers, Map<String, Object> body, String charset);

    boolean executeTask(String taskId);


}
