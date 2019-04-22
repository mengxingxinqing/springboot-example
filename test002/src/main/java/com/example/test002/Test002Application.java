package com.example.test002;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableApolloConfig
public class Test002Application {
    public static void main(String[] args) {
        SpringApplication.run(Test002Application.class, args);
    }
}
