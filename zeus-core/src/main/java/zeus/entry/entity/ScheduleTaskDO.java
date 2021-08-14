package zeus.entry.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduleTaskDO {

   private String taskId;

   private String taskName;

   private String corn;

   private List<String> jobIds;

   private String creator;

   private Date createTime;

   private String updater;

   private Date updateTime;

}
