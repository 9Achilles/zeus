package zeus.entry.dto;

import lombok.Data;

@Data
public class ZeusTaskRecordDTO extends ZeusTaskDTO{

    private String recordId;

    private Long taskId;

    //0 失败 1 成功
    private Integer status;




}
