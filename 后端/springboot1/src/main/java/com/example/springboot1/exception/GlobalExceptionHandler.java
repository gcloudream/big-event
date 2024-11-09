package com.example.springboot1.exception;

import ch.qos.logback.core.util.StringUtil;
import com.example.springboot1.pojo.Result;
import com.mysql.cj.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerExceptionhandler(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.isNullOrEmpty(e.getMessage()) ? "服务器异常" : e.getMessage());
    }
}
