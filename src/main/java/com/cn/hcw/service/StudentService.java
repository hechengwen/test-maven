package com.cn.hcw.service;

import com.cn.hcw.entity.Student;

import java.util.List;

public interface StudentService {

    int deleteByPrimaryKey(String key);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}