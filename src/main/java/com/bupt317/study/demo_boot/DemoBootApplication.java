package com.bupt317.study.demo_boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bupt317.study.demo_boot.mapper")
public class DemoBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoBootApplication.class, args);
    }
}
