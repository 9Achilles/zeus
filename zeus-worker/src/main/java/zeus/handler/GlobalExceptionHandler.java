package zeus.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zeus.exception.ZeusWorkerException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ZeusWorkerException.class})
    public void systemExceptionHandler(ZeusWorkerException e) {
        String error = e.getMessage();
        if(StringUtils.isNoneBlank(error)){
            System.out.println(error);
        }else {
            System.out.println(e);
        }
    }


}
