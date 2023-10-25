package com.example.springboot.exception;

import lombok.Getter;

/**
 * @Classname ServiceException
 * @Description TODO
 * @Author zjj
 * @Date 10/24/23 12:02AM
 */
@Getter
public class ServiceException extends RuntimeException{
    private final String code;
    public ServiceException(String msg){
        super(msg);
        this.code = "500"; //default
    }

    public ServiceException(String msg, String code){
        super(msg);
        this.code = code;
    }
}
