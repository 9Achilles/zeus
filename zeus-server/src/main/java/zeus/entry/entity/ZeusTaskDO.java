package zeus.entry.entity;

import lombok.Data;
import zeus.constant.ZeusZKDeptConstant;

import java.util.Date;
import java.util.Map;

@Data
public class ZeusTaskDO {

   private Long taskId;

   private String taskName;

   private String dept = ZeusZKDeptConstant.ZEUS_PUBLIC_DEPT;

   private String name;

   private String cron;

   private Integer policy;

   private Integer retry;

   private String url;

   private String method;

   private Map<String,String> head;

   private Map<String,String> body;

   private String description;

   private Boolean open;

   private Date createTime;

   private Date updateTime;

   private Date executeTime;

}
