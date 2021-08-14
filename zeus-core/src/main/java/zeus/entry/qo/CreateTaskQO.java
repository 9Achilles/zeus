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

    @NotNull(message = "调度执行器id不能为空")
    @NotEmpty(message = "调度执行器数据不能为空")
    private List<String> executeIds;

    @NotBlank(message = "用户名不能为空")
    private String creator;


    private String updater;

    //优先级
    private Integer level;

    //分片tag
    private boolean shardingFlag;

    //任务分片
    private Integer shardingType;

    //重试次数
    @NotNull(message = "重试次数不能为空")
    private Integer retryTimes;

    //超时时间
    @NotNull(message = "超时时间不能为空")
    private Long outTime;

}
