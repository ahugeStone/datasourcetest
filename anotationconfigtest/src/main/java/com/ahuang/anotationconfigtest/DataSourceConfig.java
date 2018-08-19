package com.ahuang.anotationconfigtest;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


/**
 * 数据源定义类
 *
 * @author ahuang
 * @version V1.0
 * @Title: DataSourceConfig
 * @Program: datasourcetest
 * @Package com.ahuang.anotationconfigtest
 * @create 2018-08-19 14:11
 */
@Slf4j
@Configuration
@MapperScan(basePackages="com.ahuang.anotationconfigtest.mapper", sqlSessionTemplateRef = "testSqlSessionTemplate")
public class DataSourceConfig {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Bean(name="druidDataSource",initMethod="init", destroyMethod="close")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(propertiesConfig);
//        dataSource.setUrl("jdbc:mysql://localhost:3307/book?useAffectedRows=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false");
//        dataSource.setUsername("root");
//        dataSource.setPassword("huangshi01!");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
    @Bean(name="druidSqlSessionFactory")
    public SqlSessionFactory druidSqlSessionFactory(@Qualifier("druidDataSource") DruidDataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        bean.setTypeAliasesPackage("com.ahuang.anotationconfigtest.entity");
        return  bean.getObject();
    }
    @Bean(name="testSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("druidSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
