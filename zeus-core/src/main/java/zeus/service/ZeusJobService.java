package zeus.service;


import zeus.entry.Response;
import zeus.entry.qo.CreateJobQO;


public interface ZeusJobService {

    Response addJob(CreateJobQO qo);



}
