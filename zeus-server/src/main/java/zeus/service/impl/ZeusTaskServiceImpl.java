//package zeus.service.impl;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//import zeus.entry.Response;
//import zeus.entry.dto.ZeusTaskDTO;
//import zeus.entry.qo.ZeusTaskQO;
//import zeus.entry.vo.ZeusTaskVO;
//import zeus.entry.vo.ZeusTasksVO;
//import zeus.manager.ZeusTaskManager;
//import zeus.service.ZeusTaskService;
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ZeusTaskServiceImpl implements ZeusTaskService {
//
//    @Resource
//    private ZeusTaskManager zeusTaskManager;
//
//    @Override
//    public Response createTask(ZeusTaskQO qo){
//
////        try {
////            ZeusTaskDTO dto = new ZeusTaskDTO();
////            BeanUtils.copyProperties(qo,dto);
////
////            if(Optional.ofNullable(zeusTaskManager.queryByUrlAndName(dto)).isPresent()){
////                return Response.error(500,"任务存在");
////            }
////
////            Date date = new Date();
////            dto.setCreateTime(date);
////            dto.setUpdateTime(date);
////            zeusTaskManager.createTask(dto);
////            return Response.success(200);
////        }catch (Exception exception){
////            return Response.error(500,"创建调度任务失败");
////        }
//        return null;
//    }
//
//    @Override
//    public Response deleteTask(Long taskId) {
////        try {
////            ZeusTaskDTO dto = zeusTaskManager.queryById(taskId);
////
////            if(!Optional.ofNullable(dto).isPresent()){
////                return Response.error(500,"删除调度任务失败");
////            }
////
////            if(dto.getOpen()){
////                return Response.error(500,"删除调度任务失败");
////            }
////
////            zeusTaskManager.deleteTaskById(taskId);
////            return Response.success(200);
////        } catch (Exception exception) {
////            return Response.error(500,"删除调度任务失败");
////        }
//        return null;
//    }
//
//    @Override
//    public Response updateTask(ZeusTaskQO qo) {
////        try {
////            ZeusTaskDTO dto = zeusTaskManager.queryById(qo.getTaskId());
////            if(!Optional.ofNullable(dto).isPresent()){
////                return Response.error(200,"编辑调度任务失败");
////            }
////
////            Date createTime = dto.getCreateTime();
////            Date updateTime = new Date();
////
////            BeanUtils.copyProperties(qo,dto);
////
////            dto.setCreateTime(createTime);
////            dto.setUpdateTime(updateTime);
////
////            zeusTaskManager.updateTaskById(dto);
////            return Response.success(200);
////        }catch (Exception exception){
////            return Response.error(500,"编辑调度任务失败");
////        }
//        return null;
//    }
//
//    @Override
//    public Response<ZeusTasksVO> queryByPage(ZeusTaskQO qo) {
////        ZeusTaskDTO dto = new ZeusTaskDTO();
////        BeanUtils.copyProperties(qo,dto);
////
////        List<ZeusTaskDTO> list = zeusTaskManager.queryByPage(dto);
////        Integer total = zeusTaskManager.queryCounts(dto);
////
////        ZeusTasksVO vo = new ZeusTasksVO();
////        vo.setTotal(total);
////        List<ZeusTaskVO> vos = new ArrayList<>();
////        list.stream().forEach(a -> {
////            ZeusTaskVO taskVO = new ZeusTaskVO();
////            BeanUtils.copyProperties(a,taskVO);
////            vos.add(taskVO);
////        });
////        vo.setList(vos);
////        vo.setCurPage(qo.getCurPage());
////        vo.setPageSize(qo.getPageSize());
////
////        return Response.success(vo);
//        return null;
//    }
//
//    @Override
//    public Response<ZeusTaskVO> queryById(Long taskId) {
//
////        ZeusTaskDTO dto = zeusTaskManager.queryById(taskId);
////
////        ZeusTaskVO vo = new ZeusTaskVO();
////        BeanUtils.copyProperties(dto,vo);
////
////        return Response.success(vo);
//
//        return null;
//    }
//
//
//    @Override
//    public Response runTask(ZeusTaskQO qo) {
////        ZeusTaskDTO dto = new ZeusTaskDTO();
////        BeanUtils.copyProperties(qo,dto);
////        try {
////            zeusTaskManager.runTaskOnce(dto);
////            return Response.success(200);
////        }catch (Exception exception){
////            return Response.error(500,"调度任务发送成功");
////        }
//
//        return null;
//
//    }
//
//
//
//}
