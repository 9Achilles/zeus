package zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeus.entry.Response;
import zeus.entry.dto.ExecutorDTO;
import zeus.entry.qo.CreateExecutorQO;
import zeus.entry.qo.ExecutorQueryQO;
import zeus.entry.qo.ExecutorRegisterQO;
import zeus.entry.qo.UpdateRegisterQO;
import zeus.manager.ZeusExecutorManager;
import zeus.service.ZeusExecutorService;

import java.util.Optional;

@Service
public class ZeusExecutorServiceImpl implements ZeusExecutorService {

    @Autowired
    private ZeusExecutorManager zeusExecutorManager;


    @Override
    public Response registerExecutor(ExecutorRegisterQO executorRegisterQO) throws Exception {

//        //code exist
//        ExecutorDTO executorDTO = zeusExecutorManager.queryByCode();
//        if(Optional.ofNullable(executorDTO).isPresent()){
//            //create zk node
//
//            // add
//            zeusExecutorManager.insert(executorDTO);
//
//            // delete redis
//
//        }else {
//            //update zk node
//
//            //update
//            zeusExecutorManager.updateByCode(executorDTO);
//
//            // delete redis
//        }
        return Response.success("success");
    }

    @Override
    public Response addExecutor(CreateExecutorQO createExecutorQO) {

        //create zk node

        // add
//        zeusExecutorManager.insert(executorDTO);


        return null;
    }

    @Override
    public Response updateExecutor(UpdateRegisterQO updateRegisterQO) {

        //update mysql

        //delete redis

        return null;
    }

    @Override
    public Response deleteExecutor(Long executorId) {

        //delete mysql

        //delete redis

        return null;
    }

    @Override
    public Response queryExecutorByPage(ExecutorQueryQO executorQueryQO) {

        //query mysql


        return null;
    }

    @Override
    public Response check(ExecutorRegisterQO executorRegisterQO) {

        //add node expire


        return null;
    }

    @Override
    public Response createExecutorCode() {


        //make uuid



        return null;
    }

    //心跳监听
    @Override
    public void process() {

        //process

        //delete mysql

        //delete redis

    }

}
