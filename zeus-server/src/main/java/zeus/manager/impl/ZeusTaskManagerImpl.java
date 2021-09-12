package zeus.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import zeus.entry.dto.ZeusTaskDTO;
import zeus.entry.entity.ZeusTaskDO;
import zeus.exception.ZeusServerException;
import zeus.manager.ZeusTaskManager;
import zeus.service.ZeusServerCuratorService;

import java.util.*;

@Component
public class ZeusTaskManagerImpl implements ZeusTaskManager {

    @Autowired
    private ZeusServerCuratorService zeusServerCuratorService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void createTask(ZeusTaskDTO dto) throws Exception {

        ZeusTaskDO taskDO = new ZeusTaskDO();
        //zeusTaskMapper.insert(taskDO);
    }

    @Override
    public void deleteTaskById(Long taskId) throws Exception {
        //zeusTaskMapper.deleteById(taskId);
    }

    @Override
    public List<ZeusTaskDTO> queryByPage(ZeusTaskDTO queryTaskPageDTO) {
        return null;
    }

    @Override
    public Integer queryCounts(ZeusTaskDTO queryTaskPageDTO) {
        return null;
    }

    @Override
    public ZeusTaskDTO queryById(Long taskId) {
        return null;
    }

    @Override
    public ZeusTaskDTO queryByUrlAndName(ZeusTaskDTO dto) {
        //url dept name
        return null;
    }

    @Override
    public void updateTaskById(ZeusTaskDTO updateTaskDTO) {

    }

    @Override
    public void runTaskByKafka(ZeusTaskDTO dto) throws ZeusServerException {

        StringJoiner stringJoiner = new StringJoiner("/", "/", "");
        String path = stringJoiner.add(dto.getDept()).add(dto.getName()).toString();

        if (!zeusServerCuratorService.checkNodeExist(path)) {
            return;
        }

        List<String> child = zeusServerCuratorService.getChild(path);
        String host = getHost(child, path);
        String data = buildTask(dto, host);
        kafkaTemplate.send("ZEUS_WORKER_SUBSCRIBE_TOPIC", data);

    }

    @Override
    public void runTaskOnce(ZeusTaskDTO dto) {


    }

    private String buildTask(ZeusTaskDTO dto, String host) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("dept", dto.getDept());
        jsonObject.put("name", dto.getName());
        jsonObject.put("url", dto.getUrl());
        jsonObject.put("host", host);
        jsonObject.put("method", dto.getMethod());
        jsonObject.put("header", dto.getHead());
        jsonObject.put("body", dto.getBody());

        return JSON.toJSONString(jsonObject);
    }

    private String getHost(List<String> hosts, String path) throws ZeusServerException {
        if (hosts.size() == 0) {
            throw new ZeusServerException("no worker in path:".concat(path));
        }
        int max = hosts.size() - 1;
        int min = 0;

        Random random = new Random();
        int index = random.nextInt(max - min + 1);
        return hosts.get(index);
    }

    public List<ZeusTaskDTO> queryByExecuteTime(Date executeTime){
        return null;
    }
}
