package zeus.service;

import zeus.entry.Response;
import zeus.entry.qo.CreateTaskQO;

public interface ZeusTaskService {

    Response createTask(CreateTaskQO createTaskQO);


}
