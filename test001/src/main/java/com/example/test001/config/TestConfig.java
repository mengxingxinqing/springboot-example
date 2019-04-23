package com.example.test001.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    public String getTestkey() {
        return testkey;
    }

    public void setTestkey(String testkey) {
        this.testkey = testkey;
    }

    @Value("${com.example.test001.testkey:'aaaaa'}")
    private String testkey;
}
