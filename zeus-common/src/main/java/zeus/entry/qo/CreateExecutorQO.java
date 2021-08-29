package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateExecutorQO {

    @NotBlank(message = "执行器名不能为空")
    private String applicationCode;

    @NotBlank(message = "执行器编码不能为空")
    private String applicationName;

    private String description;

    //[]
    private String host;
}
