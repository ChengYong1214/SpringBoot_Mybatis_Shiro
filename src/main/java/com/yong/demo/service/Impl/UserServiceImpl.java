package com.yong.demo.service.Impl;

import com.yong.demo.dao.UserDao;
import com.yong.demo.pojo.User;
import com.yong.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String username) {
        // 查询用户是否存在
        User user = userDao.findByName(username);
        if (user != null) {
            // 查询用户信息、角色、权限
            user = userDao.findById(user.getId());
        }
        return user;
    }
}
