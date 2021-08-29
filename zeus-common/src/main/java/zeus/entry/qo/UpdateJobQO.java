package zeus.entry.qo;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UpdateJobQO {

    @NotNull(message = "需更改的执行任务id不能为空")
    private Long id;

    //执行任务名
    private String jobName;

    private String jobUrl;

    //json
    private String header;

    //json
    private String body;

    private String description;

    private Long taskId;

    //重试次数
    private Integer retryTimes;

    //超时时间 5s
    private Long outTime;

    //补偿标记
    private Integer compensateFlag;

    //host调度策略 默认随机
    private Integer policy;

    //启用状态 默认禁用
    private Integer state;


}
