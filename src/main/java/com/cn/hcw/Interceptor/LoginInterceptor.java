package com.cn.hcw.Interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/7/10 0010
 * Description:
 * Others:
 */
@Component
@Aspect
public class LoginInterceptor {
    @Pointcut("execution(* com.cn.hcw.service.impl.LoginServiceImpl.userLogin(..))")
    public void login(){}

    @AfterReturning(pointcut = "login()",returning = "object")
    public void afterRuturning(JoinPoint joinPoint, Object object){
        Object[] requestParams = joinPoint.getArgs();
        String loginName = (String) requestParams[0];
        String password =  (String) requestParams[1];
        System.err.println("afterReturning.................,joinPoint args : [ loginName = " + loginName + " ],[password = " + password + "], object = " + object);
    }

    @Before("execution(* com.cn.hcw.service.impl.LoginServiceImpl.userLogin(..)))")
    public void before(JoinPoint joinPoint){
        Object obj = joinPoint.getTarget();
        Object[] objects = joinPoint.getArgs();
        System.err.println("before..............."+joinPoint.getArgs()[0]);
    }

    @After("execution(* com.cn.hcw.service.impl.LoginServiceImpl.userLogin(..)))")
    public void after(JoinPoint joinPoint){
        Object obj = joinPoint.getTarget();
        Object[] objects = joinPoint.getArgs();
        System.err.println("after................" + joinPoint.getArgs());
    }

    @AfterThrowing("execution(* com.cn.hcw.service.impl.LoginServiceImpl.userLogin(..)))")
    public void afterThrowing(){
        System.err.println("afterThrowing..............");
    }
}
