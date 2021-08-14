package zeus.manager;

import zeus.entry.dto.CreateJobDTO;
import zeus.entry.qo.CreateJobQO;

public interface ZeusJobManager {

    String addJob(CreateJobDTO qo);


}
