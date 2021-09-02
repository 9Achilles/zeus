package zeus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zeus.entry.Response;
import zeus.entry.dto.CreateJobDTO;
import zeus.entry.qo.CreateJobQO;
import zeus.entry.qo.QueryJobByPageQO;
import zeus.entry.qo.UpdateJobQO;
import zeus.service.ZeusJobService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * RPC 任务
 */
@RestController
@RequestMapping("/zeus/job")
public class ZeusJobController {

    @Autowired
    private ZeusJobService zeusJobService;

    @PostMapping("/add")
    public Response addJob(@NotNull @Valid @RequestBody CreateJobQO createJobQO) {
        return zeusJobService.addJob(createJobQO);
    }

    @PostMapping("/delete")
    public Response deleteJob(@NotNull  @RequestParam("id") Long jobId) {
        return zeusJobService.deleteJob(jobId);
    }

    @PostMapping("/update")
    public Response updateJob(@NotNull @Valid @RequestBody UpdateJobQO updateJobQO) {
        return zeusJobService.updateJob(updateJobQO);
    }

    @PostMapping("/queryByPage")
    public Response queryJobByPage(@NotNull @Valid @RequestBody QueryJobByPageQO queryJobByPageQO) {
        return zeusJobService.queryJobByPage(queryJobByPageQO);
    }


}
