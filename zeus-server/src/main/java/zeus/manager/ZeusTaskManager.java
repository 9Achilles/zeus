package zeus.manager;


import zeus.entry.dto.ZeusTaskDTO;
import zeus.exception.ZeusServerException;


import java.util.Date;
import java.util.List;

public interface ZeusTaskManager {

     void createTask(ZeusTaskDTO dto) throws Exception;

     void deleteTaskById(Long taskId)throws Exception;

     List<ZeusTaskDTO> queryByPage(ZeusTaskDTO queryTaskPageDTO);

     Integer queryCounts(ZeusTaskDTO queryTaskPageDTO);

     ZeusTaskDTO queryById(Long taskId);

     ZeusTaskDTO queryByUrlAndName(ZeusTaskDTO dto);

     void updateTaskById(ZeusTaskDTO updateTaskDTO);

     void runTaskByKafka(ZeusTaskDTO dto) throws ZeusServerException;

     void runTaskOnce(ZeusTaskDTO dto);

     List<ZeusTaskDTO> queryByExecuteTime(Date executeTime);

}
