package zeus.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zeus.entry.Response;
import zeus.entry.dto.CreateTaskDTO;
import zeus.entry.dto.QueryTaskPageDTO;
import zeus.entry.dto.UpdateTaskDTO;
import zeus.entry.entity.ZeusTaskDO;
import zeus.entry.qo.CreateTaskQO;
import zeus.entry.vo.QueryTaskListPageVO;
import zeus.entry.vo.QueryTaskVO;
import zeus.manager.ZeusTaskManager;

import java.util.List;

//do
@Component
public class ZeusTaskManagerImpl implements ZeusTaskManager {

//    @Autowired
//    private ZeusTaskMapper zeusTaskMapper;

    //@Transactional(rollbackFor = Exception.class)
    @Override
    public void createTask(CreateTaskDTO dto) throws Exception{

        ZeusTaskDO taskDO = new ZeusTaskDO();
        //zeusTaskMapper.insert(taskDO);
    }

    @Override
    public void deleteTaskById(Long taskId) throws Exception {
        //zeusTaskMapper.deleteById(taskId);
    }

    @Override
    public List<QueryTaskListPageVO> queryByPage(QueryTaskPageDTO queryTaskPageDTO) {
        return null;
    }

    @Override
    public Long queryCounts(QueryTaskPageDTO queryTaskPageDTO) {
        return null;
    }

    @Override
    public QueryTaskVO queryById(Long taskId) {
        return null;
    }

    @Override
    public void updateTaskById(UpdateTaskDTO updateTaskDTO) {

    }
}
