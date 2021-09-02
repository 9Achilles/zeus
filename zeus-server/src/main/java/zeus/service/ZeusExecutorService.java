package zeus.service;

import zeus.entry.Response;
import zeus.entry.qo.CreateExecutorQO;
import zeus.entry.qo.ExecutorQueryQO;
import zeus.entry.qo.ExecutorRegisterQO;
import zeus.entry.qo.UpdateRegisterQO;

public interface ZeusExecutorService {

    Response registerExecutor(ExecutorRegisterQO executorRegisterQO) throws Exception;

    Response addExecutor(CreateExecutorQO createExecutorQO);

    Response updateExecutor(UpdateRegisterQO updateRegisterQO);

    Response deleteExecutor(Long executorId);

    Response queryExecutorByPage(ExecutorQueryQO executorQueryQO);

    Response check(ExecutorRegisterQO executorRegisterQO);

    Response createExecutorCode();

    void process();
}
