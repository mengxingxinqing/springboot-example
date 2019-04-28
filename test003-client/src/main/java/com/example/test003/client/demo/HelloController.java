package com.example.test003.client.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Reference(version = "1.0.0")
    HelloService helloService;

    @GetMapping("sayHello")
    public String sayHello(String name){
        return helloService.SayHello(name);
    }
}
