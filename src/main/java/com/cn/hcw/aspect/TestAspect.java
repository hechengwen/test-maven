package com.cn.hcw.aspect;


import org.aspectj.lang.JoinPoint;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/6/29 0029
 * Description:
 * Others:
 */
public class TestAspect {
    public void before(Object param){
        System.err.println("before advice.........,传入的参数为：" + param);
    }

    public void after(Object param){
        System.err.println("after advice.........,传入的参数为：" + param);
    }

    public void afterReturningAdvice(JoinPoint joinPoint,Object param) {
        System.err.println("after returning advice...,返回的参数为：" + param + ";上送参数为：" + joinPoint.getArgs()[0] + joinPoint.getArgs()[1]);
    }

    public void afterThrowingAdvice(Exception exception) {
        System.err.println("after throwing advice exception..." + exception);
    }

}
