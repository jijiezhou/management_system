package com.example.sprintboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname WebController
 * @Description provide interface and return data
 * @Author zjj
 * @Date 10/22/23 6:40 PM
 */
@RestController
public class WebController {
    @RequestMapping
    public String hello(){
        return "Hello SpringBoot";
    }
}
