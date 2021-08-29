package zeus.service;

import java.util.List;

public interface ZeusExecutorService {

    List<String> queryHostByJobId(String jobId);

}
