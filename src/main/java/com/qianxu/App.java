package com.qianxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.qianxu.mapper")
@ComponentScan(basePackages = "com.qianxu")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
