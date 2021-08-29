package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateTaskQO {

    @NotNull(message = "调度任务id不能为空")
    private Long Id;

    private String taskName;

    private String corn;

    private String description;

    private Long executorId;

    //重试次数
    private Integer retryTimes;

    //超时时间
    private Long outTime;

    //补偿标记
    private Integer compensateFlag;

}
