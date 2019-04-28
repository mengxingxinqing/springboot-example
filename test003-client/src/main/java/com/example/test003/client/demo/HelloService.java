package com.example.test003.client.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public interface HelloService {
    String SayHello(String name);
}
