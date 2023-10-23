package com.example.springboot.entity;

import lombok.Data;


/**
 * @Classname User
 * @Description mapping to dataset user
 * @Author zjj
 * @Date 10/22/23 8:56 PM
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;
}
