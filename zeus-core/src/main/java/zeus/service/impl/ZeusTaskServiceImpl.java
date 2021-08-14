package zeus.service.impl;

import org.springframework.stereotype.Service;
import zeus.entry.Response;
import zeus.entry.qo.CreateTaskQO;
import zeus.mapper.ZeusTaskMapper;
import zeus.service.ZeusTaskService;

import javax.annotation.Resource;

@Service
public class ZeusTaskServiceImpl implements ZeusTaskService {

    @Resource
    private ZeusTaskMapper taskMapper;

    @Override
    public Response createTask(CreateTaskQO createTaskQO) {

//        //创造定时任务
//        taskMapper.insert(createTaskQO);

        return null;
    }
}
