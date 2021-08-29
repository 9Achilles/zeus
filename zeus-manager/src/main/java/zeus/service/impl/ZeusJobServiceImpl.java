package zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zeus.entry.Response;
import zeus.entry.dto.CreateJobDTO;
import zeus.entry.qo.CreateJobQO;
import zeus.entry.qo.QueryJobByPageQO;
import zeus.entry.qo.UpdateJobQO;
import zeus.manager.ZeusJobManager;
import zeus.service.ZeusJobService;

/**
 * 业务组装层
 */
@Component
public class ZeusJobServiceImpl implements ZeusJobService {

    @Autowired
    private ZeusJobManager zeusJobManager;


    @Override
    public Response addJob(CreateJobQO qo) {

        CreateJobDTO jobDTO = null;

//        String = zeusJobManager.addJob(jobDTO);
        return null;
    }

    @Override
    public Response deleteJob(Long jobId) {
        return null;
    }

    @Override
    public Response updateJob(UpdateJobQO updateJobQO) {
        return null;
    }

    @Override
    public Response queryJobByPage(QueryJobByPageQO queryJobByPageQO) {
        return null;
    }


}
