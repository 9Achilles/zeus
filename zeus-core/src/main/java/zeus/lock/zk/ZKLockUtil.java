package zeus.lock.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class ZKLockUtil {

    @Resource(name = "ZKCurator")
    private static CuratorFramework ZKCurator;

    //互斥锁
    public static boolean lock(String path) {

        InterProcessLock lock = new InterProcessSemaphoreMutex(ZKCurator, path);

        try {
            return lock.acquire(100L, TimeUnit.MILLISECONDS);
        } catch (Exception exception) {
            return false;
        }

    }

    public static boolean unlock(InterProcessLock lock) {
        try {
            lock.release();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


}
