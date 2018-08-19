package com.ahuang.anotationconfigtest;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
public class AnotationconfigtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnotationconfigtestApplication.class, args);
    }
}
