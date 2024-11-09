package com.example.springboot1.service;

import com.example.springboot1.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    User login(String username, String password);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePwd(String newPwd);
}
