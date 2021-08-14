package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateJobQO {


    //执行任务名
    @NotBlank(message = "执行任务名不能为空")
    private String jobName;

    //非必填
    private List<String> hosts;

    //host调度策略
    private int policy;

    //创建用户
    @NotBlank(message = "创建用户不能为空")
    private String creator;


}
