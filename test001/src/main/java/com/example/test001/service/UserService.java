package com.example.test001.service;

import com.example.test001.model.User;

public interface UserService {
    public User getById(int id);
    public int addUser(User u);
}
