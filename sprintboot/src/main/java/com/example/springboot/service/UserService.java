package com.example.springboot.service;

import cn.hutool.core.util.RandomUtil;
import com.example.springboot.common.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ServerCloneException;
import java.util.*;

/**
 * @Classname UserService
 * @Description TODO
 * @Author zjj
 * @Date 10/22/23 9:06 PM
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public void batchDeleteUsers(List<Integer> ids) {
        for (Integer id: ids){
            userMapper.deleteUser(id);
        }

    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public List<User> selectByMore(String username, String name) {
        return userMapper.selectByMore(username, name);
    }

    public List<User> selectByVague(String username, String name) {
        return userMapper.selectByVague(username, name);
    }

    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
        Integer skipNum = (pageNum - 1) * pageSize;

        Page<User> page = new Page<>();
        List<User> userList = userMapper.selectByPage(skipNum, pageSize, username, name);
        Integer total = userMapper.selectCountByPage(username, name);
        page.setList(userList);
        page.setTotal(total);
        return page;
    }

    //Validify user account is work: by username
    public User login(User user) {
        //username select
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser == null){
            //throw defined exception
            throw new ServiceException("username or password incorrect");
        }
        //user.getPassword in first, because we already check in the front end
        if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("username or password incorrect");
        }
        return dbUser;
    }

    public User register(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null){
            //throw defined exception
            throw new ServiceException("username already exists!");
        }
        user.setName(user.getUsername());
        userMapper.insert(user);
        return user;
    }
}
