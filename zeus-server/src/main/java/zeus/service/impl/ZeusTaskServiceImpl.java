package zeus.service.impl;

import org.springframework.stereotype.Service;
import zeus.constant.ResponseConstant;
import zeus.entry.Response;
import zeus.entry.dto.CreateTaskDTO;
import zeus.entry.dto.QueryTaskPageDTO;
import zeus.entry.dto.UpdateTaskDTO;
import zeus.entry.qo.*;
import zeus.entry.vo.QueryTaskListPageVO;
import zeus.entry.vo.QueryTaskPageVO;
import zeus.entry.vo.QueryTaskVO;
import zeus.manager.ZeusTaskManager;
import zeus.service.ZeusTaskService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ZeusTaskServiceImpl implements ZeusTaskService {

    @Resource
    private ZeusTaskManager zeusTaskManager;

    @Override
    public Response createTask(CreateTaskQO createTaskQO){

        try {
            //创造定时任务
            CreateTaskDTO taskDTO = new CreateTaskDTO();
            zeusTaskManager.createTask(taskDTO);
            return Response.success(ResponseConstant.SUCCESS);
        }catch (Exception exception){
            return Response.error(ResponseConstant.SYSTEM_ERROR,"创建调度任务失败");
        }
    }

    @Override
    public Response deleteTask(Long taskId) {
        try {
            //查询任务是否被绑定

            zeusTaskManager.deleteTaskById(taskId);
            return Response.success(ResponseConstant.SUCCESS);
        } catch (Exception exception) {
            return Response.error(ResponseConstant.SYSTEM_ERROR,"删除调度任务失败");
        }
    }

    @Override
    public Response updateTask(UpdateTaskQO updateTaskQO) {
        //查询

        try {
            UpdateTaskDTO dto = new UpdateTaskDTO();
            zeusTaskManager.updateTaskById(dto);
            return Response.success(ResponseConstant.SUCCESS);
        }catch (Exception exception){
            return Response.error(ResponseConstant.SYSTEM_ERROR,"编辑调度任务失败");
        }
    }

    @Override
    public Response queryByPage(QueryTaskPageQO queryTaskPageQO) {

        QueryTaskPageDTO dto = new QueryTaskPageDTO();
        List<QueryTaskListPageVO> list = zeusTaskManager.queryByPage(dto);
        Long counts = zeusTaskManager.queryCounts(dto);

        QueryTaskPageVO vo = new QueryTaskPageVO();
        vo.setCounts(counts);
        vo.setList(list);
        return Response.success(vo);
    }

    @Override
    public Response queryById(Long taskId) {
        QueryTaskVO taskVO = zeusTaskManager.queryById(taskId);
        return Response.success(taskVO);
    }

    @Override
    public Response stopTask(StopTaskQO stopTaskQO) {
        //kafka
        return Response.success(stopTaskQO);
    }

    @Override
    public Response runTask(RunTaskQO runTaskQO) {
        //直接 rpc过去
        return null;
    }

    @Override
    public Response enableTask(RunTaskQO runTaskQO) {
        //kafka
        return null;
    }

    @Override
    public Response queryJobByTaskId(Long taskId) {
        return null;
    }


}
