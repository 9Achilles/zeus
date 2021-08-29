package zeus.callback;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;

public final class ZeusCallBack implements FutureCallback<HttpResponse> {


    @Override
    public void completed(HttpResponse httpResponse) {
        //保存成功记录
        System.out.println("===========ok=============");
    }

    @Override
    public void failed(Exception e) {
        //保存失败记录
        System.out.println("=============fail==============");
        //通知

        //补偿 设置一个补偿线程池 每个任务重试n次 n次结束后如果还没有成功 就入kafka由调用方自己解决

    }

    @Override
    public void cancelled() {
        //quxiao
        System.out.println("===========cancel==============");
    }
}
