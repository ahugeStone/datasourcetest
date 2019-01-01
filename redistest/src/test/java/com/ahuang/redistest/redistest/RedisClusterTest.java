package com.ahuang.redistest.redistest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * test
 *
 * @author ahuang
 * @version V1.0
 * @Title: RedisClusterTest
 * @Program: datasourcetest
 * @Package com.ahuang.redistest.redistest
 * @create 2018-12-25 21:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisClusterTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void testRedis() throws Exception {
        ValueOperations<String,String> operations=redisTemplate.opsForValue();
        redisTemplate.multi();
        operations.set("key2", String.valueOf(123321));
        log.info(operations.get("key2"));
        redisTemplate.exec();
//        throw new Exception("new");
    }
}
