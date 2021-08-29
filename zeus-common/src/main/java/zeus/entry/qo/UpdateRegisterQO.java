package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateRegisterQO {

    @NotNull(message = "执行器id不能为空")
    private Long id;

    private String applicationName;

    private String description;

    //[]
    private String host;

}
