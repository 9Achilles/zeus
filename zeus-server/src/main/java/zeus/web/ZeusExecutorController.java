package zeus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zeus.entry.Response;
import zeus.entry.qo.CreateExecutorQO;
import zeus.entry.qo.ExecutorQueryQO;
import zeus.entry.qo.ExecutorRegisterQO;
import zeus.entry.qo.UpdateRegisterQO;
import zeus.service.ZeusExecutorService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/zeus/executor")
public class ZeusExecutorController {

    @Autowired
    private ZeusExecutorService zeusExecutorService;


    @PostMapping("/register")
    public Response registerExecutor(@Valid @RequestBody ExecutorRegisterQO executorRegisterQO) throws Exception {
        return zeusExecutorService.registerExecutor(executorRegisterQO);
    }

    @PostMapping("/add")
    public Response addExecutor(@RequestBody @Valid CreateExecutorQO createExecutorQO) {
        return zeusExecutorService.addExecutor(createExecutorQO);
    }

    @PostMapping("/update")
    public Response updateExecutor(@RequestBody @Valid UpdateRegisterQO updateRegisterQO) {
        return zeusExecutorService.updateExecutor(updateRegisterQO);
    }

    @PostMapping("/delete")
    public Response deleteExecutor(@NotNull @RequestParam("id") Long executorId) {
        return zeusExecutorService.deleteExecutor(executorId);
    }

    @PostMapping("/queryByPage")
    public Response queryExecutorByPage(@RequestBody @Valid ExecutorQueryQO executorQueryQO){
        return zeusExecutorService.queryExecutorByPage(executorQueryQO);
    }

    @PostMapping("/check")
    public Response check(@RequestBody @Valid ExecutorRegisterQO executorRegisterQO){
        return zeusExecutorService.check(executorRegisterQO);
    }

    @PostMapping("/code")
    public Response createExecutorCode(){
        return zeusExecutorService.createExecutorCode();
    }


}
