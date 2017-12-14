package com.cn.hcw.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/8 0008
 * Description:
 * Others:
 */
@Component
@Aspect
public class DataSourceInterceptor {

    private static final String slave = "execution(* com.cn.hcw.dao.slave.*.*(..))";
    private static final String master = "execution(* com.cn.hcw.dao.*.*(..))";

    @Before(slave)
    public void setdataSourceSlave() {
        DatabaseContextHolder.setCustomerType("dataSource_slave");
    }

    @Before(master)
    public void setdataSourceMaster() {
        DatabaseContextHolder.setCustomerType("dataSource_master");
    }

    @After("execution(* com.cn.hcw.dao.slave.*.*(..)) || execution(* com.cn.hcw.dao.*.*(..))")
    public void clearCustomerType() {
        DatabaseContextHolder.clearCustomerType();
    }

}
