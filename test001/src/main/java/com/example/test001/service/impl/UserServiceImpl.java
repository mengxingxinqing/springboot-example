package com.example.test001.service.impl;

import com.example.test001.mapper.UserMapper;
import com.example.test001.model.User;
import com.example.test001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public int addUser(User u){
        return userMapper.insert(u);
    }
}
