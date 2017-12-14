package com.cn.hcw.dao;

import com.cn.hcw.entity.Book;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper {

    int deleteByPrimaryKey(String key);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(@Param("record")Book record);

    int updateByPrimaryKey(@Param("record")Book record);

}