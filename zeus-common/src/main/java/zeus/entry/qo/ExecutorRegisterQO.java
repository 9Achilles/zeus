package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ExecutorRegisterQO {

    @NotBlank(message = "执行器ip不能为空")
    private String host;

    @NotBlank(message = "执行器端口不能为空")
    private String port;

    @NotBlank(message = "执行器名字不能为空")
    private String applicationName;

    @NotBlank(message = "执行器编码不能为空")
    private String applicationCode;



}
