package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateTaskQO {

    @NotBlank(message = "调度任务名不能为空")
    private String taskName;

    @NotBlank(message = "调度时间表达式不能为空")
    private String corn;

    private String description;

    @NotNull(message = "调度执行器id不能为空")
    private Long executorId;

    @NotBlank(message = "用户名不能为空")
    private String creator;

    private String updater;


}
