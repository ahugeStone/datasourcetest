package com.ahuang.xmlconfigtest.mapper;

import com.ahuang.xmlconfigtest.entity.BookBaseInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 图书基本信息
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookBaseInfoMapper
 * @Program: shardingtest
 * @Package com.ahuang.sharding.shardingtest.mapper
 * @create 2018-08-15 22:32
 */
@Mapper
public interface BookBaseInfoMapper {
    BookBaseInfoEntity select();
}
