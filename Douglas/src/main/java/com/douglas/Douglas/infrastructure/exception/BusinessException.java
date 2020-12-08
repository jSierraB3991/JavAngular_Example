package com.douglas.Douglas.infrastructure.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String info;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, String info){
        super(message);
        this.info = info;
    }

    public static BusinessException runException(String message){
        throw new BusinessException(message);
    }

    public static BusinessException runException(String message, String info){
        throw new BusinessException(message, info);
    }
}
