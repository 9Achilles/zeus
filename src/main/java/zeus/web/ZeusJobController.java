package zeus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeus.entry.Response;
import zeus.entry.dto.CreateJobDTO;
import zeus.entry.qo.CreateJobQO;
import zeus.service.ZeusJobService;
import javax.validation.Valid;

@RestController
@RequestMapping("/zeus/job")
public class ZeusJobController {

    @Autowired
    private ZeusJobService zeusJobService;

    @PostMapping("/add")
    public Response addJob(@Valid @RequestBody CreateJobQO qo){




        return null;


    }




}
