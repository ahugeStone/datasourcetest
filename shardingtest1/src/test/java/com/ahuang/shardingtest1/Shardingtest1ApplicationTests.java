package com.ahuang.shardingtest1;

import com.ahuang.shardingtest1.entity.BookBaseInfoEntity;
import com.ahuang.shardingtest1.mapper.BookBaseInfoMapper;
import com.ahuang.shardingtest1.normalmapper.BookBaseInfoNormalMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Shardingtest1ApplicationTests {

    @Autowired
    private BookBaseInfoMapper bookBaseInfoMapper;

    @Autowired
    private BookBaseInfoNormalMapper bookBaseInfoNormalMapper;

    @Test
    public void testShardingMapper() {
        BookBaseInfoEntity entity = bookBaseInfoMapper.select();
        if(null != entity)
            log.info(entity.toString());
    }
    @Test
    public void testNormalMapper() {
        BookBaseInfoEntity entity = bookBaseInfoNormalMapper.select();
        if(null != entity)
            log.info(entity.toString());
    }
}
