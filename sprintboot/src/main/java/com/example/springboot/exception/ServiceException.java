package com.example.springboot.exception;

/**
 * @Classname ServiceException
 * @Description TODO
 * @Author zjj
 * @Date 10/24/23 12:02AM
 */
public class ServiceException extends RuntimeException{
    public ServiceException(String msg){
        super(msg);
    }
}
