package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Author zjj
 * @Date 10/22/23 9:03 PM
 */
@Mapper
public interface UserMapper {
    @Insert("insert into `user` (username, password, name, phone, email, address, avatar)"
            + "values (#{username}, #{password}, #{name}, #{phone}, #{email}, #{address}, #{avatar})")
    void insert(User user);

    @Update("update `user` set username = #{username}, password = #{password}, name = #{name}, phone = #{phone}, " +
            "email = #{email}, address = #{address}, avatar = #{avatar} where id = #{id}")
    void updateUser(User user);
}
