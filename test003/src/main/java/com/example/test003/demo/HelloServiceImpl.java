package com.example.test003.demo;

import com.alibaba.dubbo.config.annotation.Service;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public String SayHello(String name) {
        return "hello "+name;
    }
}
