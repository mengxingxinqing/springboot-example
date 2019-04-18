package com.example.test001.controller;

import com.example.test001.model.User;
import com.example.test001.request.model.UserParam;
import com.example.test001.service.UserService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("根据id获取用户信息")
    public User getById(@RequestParam("id") int id){
        return userService.getById(id);
    }
//    【注意点】自动参数转对象 参数名需要对齐
    @RequestMapping("/add")
    @ApiOperation("添加用户信息")
    public int add( UserParam param){
        return userService.addUser(param.toUser());
    }


//    【注意点】用post json的方式传参
    @RequestMapping("/addByJson")
    @ApiOperation("使用post json方式传参")
    public int addByRequestBody(@RequestBody UserParam param){
        return userService.addUser(param.toUser());
    }

}
