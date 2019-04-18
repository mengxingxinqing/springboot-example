package com.example.test001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.example.test001.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class Test001Application {

    public static void main(String[] args) {
        SpringApplication.run(Test001Application.class, args);
    }

}
