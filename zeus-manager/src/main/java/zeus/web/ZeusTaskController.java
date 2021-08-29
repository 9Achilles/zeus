package zeus.web;

import org.springframework.web.bind.annotation.*;
import zeus.entry.Response;
import zeus.entry.qo.*;
import zeus.service.ZeusTaskService;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    public Response createTask(@Valid @RequestBody CreateTaskQO createTaskQO) {
        //创建定时任务
        return zeusTaskService.createTask(createTaskQO);
    }

    @PostMapping("/delete")
    public Response delete(@NotNull @RequestParam("Id") Long taskId) {
        //删除定时任务
        return zeusTaskService.deleteTask(taskId);
    }


    @PostMapping("/update")
    public Response update(@NotNull @Valid @RequestBody UpdateTaskQO updateTaskQO) {
        //编辑定时任务
        return zeusTaskService.updateTask(updateTaskQO);
    }

    @PostMapping("/queryByPage")
    public Response queryByPage(@NotNull @RequestBody QueryTaskPageQO queryTaskPageQO) {
        //分页查询定时任务
        return zeusTaskService.queryByPage(queryTaskPageQO);
    }

    @PostMapping("/queryById")
    public Response queryByPage(@NotNull @RequestParam("Id") Long taskId) {
        //分页查询定时任务
        return zeusTaskService.queryById(taskId);
    }

    @PostMapping("/stop")
    public Response stopTask(@NotNull @Valid @RequestBody StopTaskQO stopTaskQO){
        //批量停止任务 kafka
        return zeusTaskService.stopTask(stopTaskQO);
    }

    @PostMapping("/run")
    public Response runTask(@NotNull @Valid @RequestBody RunTaskQO runTaskQO){
        //批量手动执行任务
        return zeusTaskService.runTask(runTaskQO);
    }

    @PostMapping("/enable")
    public Response enableTask(@NotNull @Valid @RequestBody RunTaskQO runTaskQO){
        //批量启动任务
        return zeusTaskService.enableTask(runTaskQO);
    }

    @PostMapping("/job")
    public Response queryJobByTaskId(@NotNull @RequestParam("Id") Long taskId){
        return zeusTaskService.queryJobByTaskId(taskId);
    }


}
