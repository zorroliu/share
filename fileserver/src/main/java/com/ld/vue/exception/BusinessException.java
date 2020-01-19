package com.ld.vue.exception;

/**
 * @Author: liud
 * @Description: 业务异常类
 * @Date: 2020/1/18 17:37
 */
public class BusinessException extends RuntimeException {
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
