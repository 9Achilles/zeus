package zeus.service;


import zeus.exception.ZeusServerException;

import java.util.List;

public interface ZeusServerCuratorService {

    List<String> getChild(String path) throws ZeusServerException;

    boolean checkNodeExist(String path) throws ZeusServerException;

    boolean createNode(String path);

    void deleteNode(String path);

}
