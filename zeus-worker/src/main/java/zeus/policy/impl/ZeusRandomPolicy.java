package zeus.policy.impl;


import org.springframework.stereotype.Service;
import zeus.policy.ZeusPolicy;
import zeus.service.ZeusExecutorService;

import java.util.List;
import java.util.Random;

@Service
public class ZeusRandomPolicy implements ZeusPolicy {

    private ZeusExecutorService zeusExecutorService;

    @Override
    public String getHost(Long jobId){

        List<String> host = zeusExecutorService.queryHostByJobId(String.valueOf(jobId));

        int max = host.size() - 1;
        int min = 0;

        Random random = new Random();
        int index =random.nextInt(max - min + 1) ;

        return host.get(index);
    }



}
