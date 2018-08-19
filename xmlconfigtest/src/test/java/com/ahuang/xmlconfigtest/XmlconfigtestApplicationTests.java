package com.ahuang.xmlconfigtest;

import com.ahuang.xmlconfigtest.mapper.BookBaseInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlconfigtestApplicationTests {

    @Autowired
    private BookBaseInfoMapper bookBaseInfoMapper;

    @Test
    public void testMapper() {
        log.info(bookBaseInfoMapper.select().toString());
    }
}
