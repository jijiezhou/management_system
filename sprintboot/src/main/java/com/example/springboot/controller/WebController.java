package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.common.Result;


/**
 * @Classname WebController
 * @Description provide interface and return data
 * @Author zjj
 * @Date 10/22/23 6:40 PM
 */
@RestController
public class WebController {
    @Resource
    UserService userService;

    @GetMapping("/")
    public Result hello(){
        return Result.success("success");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            return Result.error("Invalid Input");
        }
        user = userService.login(user);
        return Result.success(user);
    }
}
