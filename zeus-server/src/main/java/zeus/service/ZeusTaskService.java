package zeus.service;

import zeus.entry.Response;
import zeus.entry.qo.*;

public interface ZeusTaskService {

    Response createTask(CreateTaskQO createTaskQO);

    Response deleteTask(Long taskId);

    Response updateTask(UpdateTaskQO updateTaskQO);

    Response queryByPage(QueryTaskPageQO queryTaskPageQO);

    Response queryById(Long taskId);

    Response stopTask(StopTaskQO stopTaskQO);

    Response runTask(RunTaskQO runTaskQO);

    Response enableTask(RunTaskQO runTaskQO);

    Response queryJobByTaskId(Long taskId);




}
