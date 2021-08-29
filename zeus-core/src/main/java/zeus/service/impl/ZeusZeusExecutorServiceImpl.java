package zeus.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zeus.mapper.ZeusExecutorMapper;
import zeus.service.ZeusExecutorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZeusZeusExecutorServiceImpl implements ZeusExecutorService {

    private ZeusExecutorMapper zeusExecutorMapper;

    @Cacheable(key = "#jobId",cacheManager = "executorHostCache",sync = true)
    public List<String> queryHostByJobId(String jobId) {

        //

        return new ArrayList<>();
    }


}
