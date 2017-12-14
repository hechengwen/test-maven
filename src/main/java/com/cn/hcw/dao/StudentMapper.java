package com.cn.hcw.dao;

import com.cn.hcw.entity.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    int deleteByPrimaryKey(String key);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(@Param("record")Student record);

    int updateByPrimaryKey(@Param("record")Student record);

    int countByList(@Param("nameList")List list);

    int countByArray(@Param("arrays")String[] arrays);

    int countByMap(Map map);

}