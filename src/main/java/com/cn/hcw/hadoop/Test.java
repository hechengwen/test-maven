package com.cn.hcw.hadoop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/7/21 0021
 * Description:
 * Others:
 */
@ContextConfiguration("classpath:application.xml")
@ActiveProfiles("test")
public class Test extends AbstractJUnit4SpringContextTests {

    @Autowired
    private HadoopDemo hadoopDemo;
    @org.junit.Test
    public void testDemo(){
        try {
            hadoopDemo.copyFile("D:\\lala\\haha.log","/bidDataLog/20161223/market1_.1482476152777.log");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void download(){
        try {
            hadoopDemo.download("/bidDataLog/20161223/market1_.1482476152777.log","D:\\lala\\haha.log");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
