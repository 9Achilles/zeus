package zeus.service.impl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeus.exception.ZeusWorkerException;
import zeus.service.ZeusWorkerCuratorService;

import java.util.List;
import java.util.Optional;

@Service
public class ZeusWorkerCuratorServiceImpl implements ZeusWorkerCuratorService {

    @Autowired
    private CuratorFramework curatorFramework;

    @Override
    public List<String> getChild(String path) throws ZeusWorkerException{
        try {
            List<String> child = curatorFramework.getChildren().forPath(path);
            return child;
        } catch (Exception exception) {
            throw new ZeusWorkerException(exception.getMessage());
        }
    }

    @Override
    public boolean checkNodeExist(String path) throws ZeusWorkerException{
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            return Optional.of(stat).isPresent();
        } catch (Exception exception) {
            throw new ZeusWorkerException(exception.getMessage());
        }
    }

    @Override
    public void createNode(String path) throws ZeusWorkerException{
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception exception) {
            throw new ZeusWorkerException(exception.getMessage());
        }
    }


}
