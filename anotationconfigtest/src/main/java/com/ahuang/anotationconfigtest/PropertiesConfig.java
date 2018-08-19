package com.ahuang.anotationconfigtest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 读取数据源信息的配置文件
 *
 * @author ahuang
 * @version V1.0
 * @Title: PropertiesConfig
 * @Program: datasourcetest
 * @Package com.ahuang.anotationconfigtest
 * @create 2018-08-19 14:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "jdbc")
// PropertySource默认取application.properties
 @PropertySource(value = "classpath:config.properties")
public class PropertiesConfig extends Properties {
}
