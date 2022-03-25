//package zeus.service.impl;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.data.Stat;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import zeus.exception.ZeusServerException;
//import zeus.service.ZeusServerCuratorService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ZeusServerCuratorServiceImpl implements ZeusServerCuratorService {
//
//    @Autowired
//    private CuratorFramework curatorFramework;
//
//    @Override
//    public List<String> getChild(String path) throws ZeusServerException {
//        try {
//            List<String> child = curatorFramework.getChildren().forPath(path);
//            return child;
//        } catch (Exception exception) {
//            throw new ZeusServerException(exception.getMessage());
//        }
//    }
//
//    @Override
//    public boolean checkNodeExist(String path) throws ZeusServerException {
//        try {
//            Stat stat = curatorFramework.checkExists().forPath(path);
//            return Optional.of(stat).isPresent();
//        } catch (Exception exception) {
//            throw new ZeusServerException(exception.getMessage());
//        }
//    }
//
//    @Override
//    public boolean createNode(String path) {
//        try {
//            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
//            return true;
//        } catch (Exception exception) {
//            return false;
//        }
//    }
//
//    public void deleteNode(String path) {
//
//        try {
//            boolean exist = checkNodeExist(path);
//            while (exist) {
//                curatorFramework.delete().forPath(path);
//                exist = false;
//            }
//        } catch (ZeusServerException e) {
//            System.out.println("节点检查出错");
//        } catch (Exception exception) {
//            System.out.println("删除失败！继续删除");
//        }
//
//    }
//}
