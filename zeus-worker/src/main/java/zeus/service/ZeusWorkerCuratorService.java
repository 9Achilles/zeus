package zeus.service;

import zeus.exception.ZeusWorkerException;

import java.util.List;

public interface ZeusWorkerCuratorService {

    List<String> getChild(String path) throws ZeusWorkerException;

    boolean checkNodeExist(String path) throws ZeusWorkerException;

    void createNode(String path) throws ZeusWorkerException;

}
