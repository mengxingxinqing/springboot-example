package com.example.test001.controller;

import com.example.test001.model.User;
import com.example.test001.request.model.UserParam;
import com.example.test001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public User getById(@RequestParam("id") int id){
        return userService.getById(id);
    }
//    【注意点】自动参数转对象 参数名需要对齐
    @RequestMapping("/add")
    public int add( UserParam param){
        return userService.addUser(param.toUser());
    }


//    【注意点】用post json的方式传参
    @RequestMapping("/addByJson")
    public int addByRequestBody(@RequestBody UserParam param){
        return userService.addUser(param.toUser());
    }

}
