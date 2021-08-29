package zeus.rpc.impl;

import com.alibaba.fastjson.JSON;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeus.callback.ZeusCallBack;
import zeus.constant.HTTPConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import zeus.entry.entity.ZeusJobDO;
import zeus.lock.zk.ZKLockUtil;
import zeus.mapper.ZeusExecutorMapper;
import zeus.mapper.ZeusJobTaskInfoMapper;
import zeus.policy.ZeusPolicy;
import zeus.rpc.RPCService;
import zeus.threadpool.RPCPoolBean;
import java.util.Map;
import java.util.Optional;

@Service("HttpClientRPCService")
public class HttpClientRPCServiceImpl implements RPCService {

    private final CloseableHttpAsyncClient httpclient = RPCPoolBean.httpclient;

    @Autowired
    private ZeusPolicy zeusPolicy;

    @Override
    public void doPost(Long jobId) {

        //job info
        ZeusJobDO zeusJobDO = null;

        //policy host
        String host = zeusPolicy.getHost(jobId);

        //
        String url = null;
        Map<String, String> headers = null;
        Map<String, Object> body = null;
        String charset = null;

        doPost(url,headers,body,charset);

    }

    private void doPost(String url, Map<String, String> headers, Map<String, Object> body, String charset) {
        HttpPost httpPost = new HttpPost(url);
        httpPost = addHeaders(httpPost, headers);
        httpPost = addParam(httpPost, body, charset);
        httpclient.execute(httpPost, new ZeusCallBack());
    }


    private HttpPost addParam(HttpPost httpPost, Map<String, Object> body, String charset) {
        if (Optional.ofNullable(body).isPresent() && !body.isEmpty()) {
            String jsonBody = JSON.toJSONString(body);
            charset = StringUtils.isBlank(charset) ? HTTPConstant.DEFAULT_CHARSET : charset;
            StringEntity entity = new StringEntity(jsonBody, charset);
            httpPost.setEntity(entity);
        }
        return httpPost;
    }

    private HttpPost addHeaders(HttpPost httpPost, Map<String, String> headers) {
        if (Optional.ofNullable(headers).isPresent() && !headers.isEmpty()) {
            headers.keySet()
                    .stream()
                    .forEach(a -> httpPost.addHeader(a, headers.get(a)));
        }
        return httpPost;
    }



}



