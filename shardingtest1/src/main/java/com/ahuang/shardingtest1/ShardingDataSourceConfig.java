package com.ahuang.shardingtest1;


import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.core.api.ShardingDataSourceFactory;
import io.shardingsphere.core.api.config.ShardingRuleConfiguration;
import io.shardingsphere.core.api.config.TableRuleConfiguration;
import io.shardingsphere.core.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.core.constant.ShardingPropertiesConstant;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


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
@MapperScan(basePackages="com.ahuang.shardingtest1.mapper", sqlSessionTemplateRef = "shardingSqlSessionTemplate")
public class ShardingDataSourceConfig {

    @Bean(name="shardingDataSource")
    public DataSource shardingDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setUrl("jdbc:mysql://localhost:3307/book1?useAffectedRows=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource1.setUsername("root");
        dataSource1.setPassword("huangshi01!");
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceMap.put("ds0", dataSource1);

        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource2.setUrl("jdbc:mysql://localhost:3307/book1?useAffectedRows=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource2.setUsername("root");
        dataSource2.setPassword("huangshi01!");
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceMap.put("ds1", dataSource2);

        // 配置BOOK_BASEINFO表规则
        TableRuleConfiguration bookTableRuleConfig = new TableRuleConfiguration();
        bookTableRuleConfig.setLogicTable("BOOK_BASEINFO");
        bookTableRuleConfig.setActualDataNodes("ds${0..1}.BOOK_BASEINFO${0..1}");

        // 配置分库 + 分表策略
        bookTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("bookId", "ds${bookId % 2}"));
        bookTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("bookId", "BOOK_BASEINFO${bookId % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(bookTableRuleConfig);

        Properties properties = new Properties();
        properties.setProperty(ShardingPropertiesConstant.SQL_SHOW.getKey(), "true");

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), properties);
        return dataSource;
    }
    @Bean(name="shardingSqlSessionFactory")
    public SqlSessionFactory druidSqlSessionFactory(@Qualifier("shardingDataSource") DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(shardingDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        bean.setTypeAliasesPackage("com.ahuang.shardingtest1.entity");
        return  bean.getObject();
    }
    @Bean(name="shardingSqlSessionTemplate")
    public SqlSessionTemplate testShardingSqlSessionTemplate(@Qualifier("shardingSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
