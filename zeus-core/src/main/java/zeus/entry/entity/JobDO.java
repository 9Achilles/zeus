package zeus.entry.entity;

import lombok.Data;

/**
 * 用户需要填application name
 * @ZeusSchedule(name，code)
 */
@Data
public class JobDO {

    //任务唯一键
    private String jobId;

    //应用名
    private String applicationName;

    //应用code
    private String applicationCode;

    //请求url
    private String url;

    //json
    private String headers;

    //post get
    private int methodType;

    //json
    private String body;

}
