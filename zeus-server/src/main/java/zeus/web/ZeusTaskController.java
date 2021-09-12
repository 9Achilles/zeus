package zeus.web;

import org.springframework.web.bind.annotation.*;
import zeus.entry.Response;
import zeus.entry.qo.*;
import zeus.entry.vo.ZeusTasksVO;
import zeus.service.ZeusTaskService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 调度任务
 */
@RestController
@RequestMapping("/zeus/task")
public class ZeusTaskController {

    @Resource
    private ZeusTaskService zeusTaskService;

    @PostMapping("/add")
    public Response createTask(@NotNull @RequestBody ZeusTaskQO qo) {
        return zeusTaskService.createTask(qo);
    }

    @PostMapping("/delete")
    public Response delete(@NotNull @RequestParam("Id") Long taskId) {
        return zeusTaskService.deleteTask(taskId);
    }


    @PostMapping("/update")
    public Response update(@NotNull @RequestBody ZeusTaskQO qo) {
        return zeusTaskService.updateTask(qo);
    }

    @PostMapping("/queryByPage")
    public Response<ZeusTasksVO> queryByPage(@NotNull @RequestBody ZeusTaskQO qo) {
        return zeusTaskService.queryByPage(qo);
    }

    @PostMapping("/queryById")
    public Response queryByPage(@NotNull @RequestParam("Id") Long taskId) {
        return zeusTaskService.queryById(taskId);
    }


    @PostMapping("/run")
    public Response runTask(@NotNull @RequestBody ZeusTaskQO qo){
        //批量手动执行任务
        return zeusTaskService.runTask(qo);
    }



}
