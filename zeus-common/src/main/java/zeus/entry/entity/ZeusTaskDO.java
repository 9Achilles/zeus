package zeus.entry.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ZeusTaskDO {

   private String id;

   private String taskName;

   private String taskCorn;

   private String description;

   private Long executorId;

   private String jobId;

   private Integer retryTime;

   private Integer overTime;

   private Integer compensateFlag;

   private String creator;

   private Date createTime;

   private String updater;

   private Date updateTime;

}
