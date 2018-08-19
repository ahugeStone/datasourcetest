package com.ahuang.xmlconfigtest;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
@Configuration
@ImportResource("classpath:config/spring-mybatis.xml")
public class XmlconfigtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlconfigtestApplication.class, args);
    }
}
