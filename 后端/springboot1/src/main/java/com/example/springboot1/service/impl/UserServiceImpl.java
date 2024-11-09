package com.example.springboot1.service.impl;

import com.example.springboot1.mapper.UserMapper;
import com.example.springboot1.pojo.User;
import com.example.springboot1.service.UserService;
import com.example.springboot1.utils.Md5Util;
import com.example.springboot1.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User user=userMapper.findByUserName(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUserName(username);
        if (user!=null&&user.getPassword().equals(Md5Util.getMD5String(password))){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> stringObjectMap=ThreadLocalUtil.get();
        Integer id = (Integer) stringObjectMap.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> stringObjectMap=ThreadLocalUtil.get();
        Integer id = (Integer) stringObjectMap.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }


}
