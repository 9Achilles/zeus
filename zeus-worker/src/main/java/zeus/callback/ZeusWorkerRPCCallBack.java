package zeus.callback;

import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import zeus.exception.ZeusWorkerException;

import java.io.IOException;

/**
 * 报文
 * url
 * result
 * time
 * （cause）
 *
 */
public final class ZeusWorkerRPCCallBack implements FutureCallback<HttpResponse> {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @SneakyThrows
    @Override
    public void completed(HttpResponse httpResponse) {
        try {
            byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
            String data = bytes.toString();
            kafkaTemplate.send("ZEUS_RPC_SUCCESS",data);
        } catch (IOException e) {
            throw new ZeusWorkerException(e.getMessage());
        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void cancelled() {

    }
}
