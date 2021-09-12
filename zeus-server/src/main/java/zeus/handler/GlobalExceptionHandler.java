package zeus.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zeus.entry.Response;
import zeus.exception.ZeusServerException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public Response paramExceptionHandler(MethodArgumentNotValidException e) {
//        BindingResult exceptions = e.getBindingResult();
//        if (exceptions.hasErrors()) {
//            List<ObjectError> errors = exceptions.getAllErrors();
//            if (!errors.isEmpty()) {
//                FieldError fieldError = (FieldError) errors.get(0);
//                return Response.error(ResponseConstant.PARAM_ERROR,fieldError.getDefaultMessage());
//            }
//        }
//        return Response.error(ResponseConstant.PARAM_ERROR,"参数错误");
//    }

    @ExceptionHandler(ZeusServerException.class)
    public void systemExceptionHandler(ZeusServerException e) {
        String error = e.getMessage();
        if(StringUtils.isNoneBlank(error)){
            System.out.println(error);
        }else {
            System.out.println(e);
        }
    }


}
