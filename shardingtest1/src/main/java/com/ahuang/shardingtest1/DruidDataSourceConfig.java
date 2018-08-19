package com.ahuang.shardingtest1;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * druid数据源
 *
 * @author ahuang
 * @version V1.0
 * @Title: DruidDataSourceConfig
 * @Program: datasourcetest
 * @Package com.ahuang.shardingtest1
 * @create 2018-08-19 18:23
 */
@Slf4j
@Configuration
@MapperScan(basePackages="com.ahuang.shardingtest1.normalmapper", sqlSessionTemplateRef = "druidSqlSessionTemplate")
public class DruidDataSourceConfig {
    @Bean(name="druidDataSource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.configFromPropety(propertiesConfig);
        dataSource.setUrl("jdbc:mysql://localhost:3307/book?useAffectedRows=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("huangshi01!");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }
    @Bean(name="druidSqlSessionFactory")
    public SqlSessionFactory druidSqlSessionFactory(@Qualifier("druidDataSource") DataSource druidDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(druidDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/normalmapper/*.xml"));
        bean.setTypeAliasesPackage("com.ahuang.shardingtest1.entity");
        return  bean.getObject();
    }
    @Bean(name="druidSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("druidSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
