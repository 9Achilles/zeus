package zeus.service.impl;

import org.springframework.stereotype.Service;
import zeus.service.ZeusWorkerPolicy;

import java.util.List;
import java.util.Random;

@Service
public class ZeusWorkerRandomPolicyImpl implements ZeusWorkerPolicy {

    public String getHost(List<String> hosts){

        int max = hosts.size() - 1;
        int min = 0;

        Random random = new Random();
        int index =random.nextInt(max - min + 1) ;
        return hosts.get(index);
    }

}
