package com.example.springboot1.mapper;

import com.example.springboot1.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void add(String username, String password);

    @Update("update user set email=#{email},update_time=now(),nickname=#{nickname} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{userPic},update_time=now() where id=#{id}")
    void updateAvatar(String userPic,Integer id);

    @Update("update user set password=#{password},update_time=now() where id = #{id}")
    void updatePwd(String password,Integer id);
}
