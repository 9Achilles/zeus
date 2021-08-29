package zeus.entry.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateJobQO {

    //执行任务名
    @NotBlank(message = "执行任务名不能为空")
    private String jobName;

    @NotBlank(message = "执行任务url不能为空")
    private String jobUrl;

    //json
    private String header;

    //json
    private String body;

    private String description;

    @NotNull(message = "调度任务id不能为空")
    private Long taskId;

    //重试次数
    private Integer retryTimes = 0;

    //超时时间 5s
    private Long outTime = 5000L;

    //补偿标记
    private Integer compensateFlag = 0;

    //host调度策略 默认随机
    private Integer policy = 0;

    //启用状态 默认禁用
    private Integer state = 0;

}
