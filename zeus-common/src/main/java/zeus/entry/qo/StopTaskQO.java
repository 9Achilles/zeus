package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class StopTaskQO {

    @NotNull
    @NotEmpty(message = "需要停止的任务不能为空")
    private List<Long> list;


}
