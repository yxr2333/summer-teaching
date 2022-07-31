package com.ssssheep.summer.config;

import com.ssssheep.summer.pojo.dto.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created By Intellij IDEA
 *
 * @author ssssheep
 * @package com.ssssheep.summer.config
 * @datetime 2022/7/31 Sunday
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ApiResult handleException(Exception e){
        ApiResult result = null;
        e.printStackTrace();
        if(e instanceof RuntimeException){
            RuntimeException runtimeException = (RuntimeException) e;
            result = ApiResult.error(runtimeException.getMessage());
        }
//        else {
//            result = ApiResult.error(e.getMessage());
//        }
        return result;
    }

}
