package zeus.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zeus.constant.ResponseConstant;
import zeus.entry.Response;

import java.util.List;

@RestControllerAdvice("zeus.web")
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return Response.error(ResponseConstant.PARAM_ERROR,fieldError.getDefaultMessage());
            }
        }
        return Response.error(ResponseConstant.PARAM_ERROR,"参数错误");
    }


    @ResponseStatus
    @ExceptionHandler({Exception.class})
    public Response systemExceptionHandler(Exception e) {
        String error = e.getMessage();
        if(StringUtils.isNoneBlank(error)){
            return Response.error(ResponseConstant.SYSTEM_ERROR,error);
        }else {
            return Response.error(ResponseConstant.SYSTEM_ERROR,"系统异常");
        }
    }


}
