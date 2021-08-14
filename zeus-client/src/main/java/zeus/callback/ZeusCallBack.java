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
    }

    @Override
    public void cancelled() {
        //quxiao
        System.out.println("===========cancel==============");
    }
}
