package zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeus.exception.ZeusWorkerException;
import zeus.service.ZeusWorkerCuratorService;
import zeus.service.ZeusWorkerHostService;
import zeus.service.ZeusWorkerPolicy;

import java.util.List;

@Service
public class ZeusWorkerHostServiceImpl implements ZeusWorkerHostService {

    @Autowired
    private ZeusWorkerCuratorService zeusWorkerCuratorService;

    @Autowired
    private ZeusWorkerPolicy zeusWorkerPolicy;

    private List<String> getHosts(String dept,String name) throws ZeusWorkerException {

        String path = "/".concat(dept).concat("/").concat(name);
        if(!zeusWorkerCuratorService.checkNodeExist(path)){
            throw new ZeusWorkerException("path:".concat(path).concat(" is not existed!"));
        }

        List<String> hosts = zeusWorkerCuratorService.getChild(path);
        if(hosts.size() == 0){
            throw new ZeusWorkerException("path:".concat(path).concat(" has no child!"));
        }

        return hosts;
    }

    @Override
    public String getHost(String dept,String name) throws ZeusWorkerException {
        List<String> hosts = getHosts(dept,name);
        return zeusWorkerPolicy.getHost(hosts);
    }



}
