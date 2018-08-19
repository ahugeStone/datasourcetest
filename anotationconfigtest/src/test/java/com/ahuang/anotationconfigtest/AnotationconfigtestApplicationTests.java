package com.ahuang.anotationconfigtest;

import com.ahuang.anotationconfigtest.entity.BookBaseInfoEntity;
import com.ahuang.anotationconfigtest.mapper.BookBaseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnotationconfigtestApplicationTests {
    @Autowired
    private PropertiesConfig propertiesConfig;

    @Test
    public void contextLoads() {
    }

    @Autowired
    private BookBaseInfoMapper bookBaseInfoMapper;

    @Test
    public void testMapper() {
        BookBaseInfoEntity entity = bookBaseInfoMapper.select();
        if(null != entity)
            log.info(entity.toString());
    }

}
