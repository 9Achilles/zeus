package zeus.manager;

import zeus.entry.dto.CreateTaskDTO;
import zeus.entry.dto.QueryTaskPageDTO;
import zeus.entry.dto.UpdateTaskDTO;
import zeus.entry.vo.QueryTaskListPageVO;
import zeus.entry.vo.QueryTaskVO;

import java.util.List;

public interface ZeusTaskManager {

     void createTask(CreateTaskDTO dto) throws Exception;

     void deleteTaskById(Long taskId)throws Exception;

     List<QueryTaskListPageVO> queryByPage(QueryTaskPageDTO queryTaskPageDTO);

     Long queryCounts(QueryTaskPageDTO queryTaskPageDTO);

     QueryTaskVO queryById(Long taskId);

     void updateTaskById(UpdateTaskDTO updateTaskDTO);

}
