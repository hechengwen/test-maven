package com.cn.hcw.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/8 0008
 * Description:
 * Others:动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    @Override
    public Object determineCurrentLookupKey(){
        return DatabaseContextHolder.getCustomerType();
    }
}
