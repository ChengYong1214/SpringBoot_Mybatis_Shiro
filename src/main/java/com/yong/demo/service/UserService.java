package com.yong.demo.service;

import com.yong.demo.pojo.User;

public interface UserService {
    public User findByName(String username);
}
