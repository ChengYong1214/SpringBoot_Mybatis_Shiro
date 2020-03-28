package com.yong.demo.dao;

import com.yong.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public User findByName(String username); // 查询用户信息
    public User findById(Integer id);// 查询用户信息、角色、权限
}
