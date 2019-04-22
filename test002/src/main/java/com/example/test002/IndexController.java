package com.example.test002;

import com.example.test002.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class IndexController {
    @Autowired
    private TestConfig testConfig;
    @RequestMapping("/index")
    public String index(){
        return "hello "+ testConfig.getDatabase();
    }
}
