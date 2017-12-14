package com.cn.hcw.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/4 0004
 * Description:
 * Others:
 */
public abstract class BaseService extends Observable {

    protected Logger logger;

    protected String classname;

    public BaseService() {
        super();
        classname = getClass().getName();
        logger = LoggerFactory.getLogger(classname);

    }
}
