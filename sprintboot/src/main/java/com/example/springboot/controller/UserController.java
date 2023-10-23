package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @Classname UserController
 * @Description TODO
 * @Author zjj
 * @Date 10/22/23 9:06 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * add user info
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        try{
            userService.insertUser(user);
        } catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return Result.error("Insert Database Error");
            }else{
                return Result.error("Internal Fault");
            }
        }
        return Result.success();
    }

    /**
     * update user info
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }
}
