package com.cn.hcw.properties;

import com.cn.hcw.utils.security.HelloWorld;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/4/21 0021
 * Description:
 * Others:
 */
@Component
@Slf4j
public class ReadProperties extends Observable {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("com.cn.message.message", Locale.US);

    @PostConstruct
    public void init(){
        System.err.println("............@PostConstruct");
        String eat = String.format(resourceBundle.getString("today.eat"),30);
        String sport = resourceBundle.getString("today.sport");
        String read = resourceBundle.getString("today.read");
        String time = resourceBundle.getString("today.work.time");
        String subway = resourceBundle.getString("today.subway");
//        log.info("eat="+eat+",sport="+sport+",read="+read+",time="+time+",subway="+subway);
    }


    public static void main(String[] args){
        String sport = resourceBundle.getString("today.sport");
        String eat = String.format(resourceBundle.getString("today.eat"),30);
        String read = resourceBundle.getString("today.read");
        String time = resourceBundle.getString("today.work.time");
        String subway = resourceBundle.getString("today.subway");
//        log.info("eat="+eat+",sport="+sport+",read="+read+",time="+time+",subway="+subway);
//        log.info("getName == " + getName(""));
//        log.info("random = " + RandomStringUtils.randomNumeric(6));//产生6位长度的随机数字
//        log.info("random1 = " +  RandomStringUtils.random(5));//产生5位长度的随机字符串,中文环境下是乱码
//        log.info("random2 = " +  RandomStringUtils.random(5, "abcdABCD1234"));//使用指定的字符生成5位长度的随机字符串
//        log.info("random3 = " +  RandomStringUtils.randomAlphanumeric(5)); //生成指定长度的字母和数字的随机组合字符串
//        log.info("random4 = " +  RandomStringUtils.randomAlphabetic(5)); //生成随机[a-z]字符串，包含大小写

    }

    @HelloWorld(hello = "feifei")
    public static  String getName(String name){
        if(name == null || name.equals("")) {
            return "name == null or name == '' ";
        }
        return name + "say hello world!";
    }
}
