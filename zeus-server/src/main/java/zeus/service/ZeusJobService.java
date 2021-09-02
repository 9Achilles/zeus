package zeus.service;


import zeus.entry.Response;
import zeus.entry.qo.CreateJobQO;
import zeus.entry.qo.QueryJobByPageQO;
import zeus.entry.qo.UpdateJobQO;


public interface ZeusJobService {

    Response addJob(CreateJobQO qo);

    Response deleteJob(Long jobId);

    Response updateJob(UpdateJobQO updateJobQO);

    Response queryJobByPage(QueryJobByPageQO queryJobByPageQO);



}
