package com.cn.hcw.utils.security;

import java.lang.annotation.*;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/4/23 0023
 * Description:
 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
   @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
   @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * @Target：定义注解的作用目标
 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
   @Target(ElementType.FIELD) //字段、枚举的常量
   @Target(ElementType.METHOD) //方法
   @Document：说明该注解将被包含在javadoc中
   @Inherited：说明子类可以继承父类中的该注解
  * Others:
 */
@Documented
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
public @interface HelloWorld {
    String hello() default "";
}
