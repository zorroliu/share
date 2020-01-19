package com.ld.vue.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liud
 * @Description: 统一异常处理类
 * @Date: 2020/1/18 18:40
 */
@ControllerAdvice
public class ExceptionAdvice {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {
        if(e instanceof RuntimeException) {
            return e.getMessage();
        }
        return "error";
    }
}
