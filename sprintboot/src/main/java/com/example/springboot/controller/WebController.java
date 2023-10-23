package com.example.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.example.springboot.common.Result;

import java.util.List;

/**
 * @Classname WebController
 * @Description provide interface and return data
 * @Author zjj
 * @Date 10/22/23 6:40 PM
 */
@RestController
@RequestMapping("/web")
public class WebController {
    @GetMapping("/hello")
    public Result hello(String name){
        return Result.success(name);
    }

    @PostMapping("/post") //http://localhost:9090/web/post?name=jijiez need url params
    public Result post(@RequestBody Obj obj){
        System.out.println(obj.getName() == null ? "null" : obj.getName().isEmpty());
        return Result.success(obj);
    }

    @PutMapping("/put")
    public Result put(@RequestBody Obj obj){
        return Result.success(obj);
    }

    @DeleteMapping("/delete/{id}") //http://localhost:9090/web/delete/1 => /1 -> /{id}
    public Result delete(@PathVariable Integer id){
        return Result.success(id);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody List<Integer> ids){
        return Result.success(ids);
    }
}
