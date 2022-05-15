package zeus.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 任务
 *
 *
 */
@Data
public class BaseTask {

    private String taskId;

    private String corn;

    //开启状态
    private String status;

    private Date nextTime;


//    public List<BaseTask> queryByHashPolicy(){
//
//    }

    public List<BaseTask> query(){
        //从数据库查询开启的切将被执行的task返回

        return null;
    }






}
