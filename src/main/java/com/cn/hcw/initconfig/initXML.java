package com.cn.hcw.initconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by lenovo on 2017/3/16 0016.
 */
@Slf4j
public class initXML implements BeanNameAware,BeanFactoryAware,ApplicationContextAware{
    private String address;
    public void init(){
        System.err.println("配置文件初始化！！,address="+address);
    }

    public void setAddress(String address){
        System.out.println("setAddress method used");
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public initXML(){
        System.out.println("initXML被实例化！！");
    }
    @Override
    public void setBeanName(String s) {
        System.out.println("beanName="+s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("beanFactory = [" + beanFactory + "]");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.err.println("applicationContext = [" + applicationContext + "]");
    }
}
