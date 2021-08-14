package zeus.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeus.entry.Response;
import zeus.entry.qo.CreateTaskQO;
import zeus.service.ZeusTaskService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/zeus/task")
public class ZeusTaskController {

    @Resource
    private ZeusTaskService zeusTaskService;

    @PostMapping("/add")
    public Response createTask(@Valid @RequestBody CreateTaskQO createTaskQO) {
        //创建定时任务

        return null;
    }


}
