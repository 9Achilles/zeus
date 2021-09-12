package zeus.entry.vo;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ZeusTaskVO {

    private Long taskId;

    private String taskName;

    private String dept;

    private String name;

    private String corn;

    private Integer policy;

    private Integer retry;

    private String url;

    private String method;

    private Map<String,String> head;

    private Map<String,String> body;

    private String description;

    private Boolean open;

    private Date createTime;

    private Date updateTIme;


}
