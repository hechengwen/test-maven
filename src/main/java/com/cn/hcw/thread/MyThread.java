package com.cn.hcw.thread;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/3 0003
 * Description:
 * Others:
 */
public class MyThread extends Thread{

    @Override
    public void run(){
        for(int i = 0 ; i<=1000 ;i++ ){
            System.out.println(Thread.currentThread().getName() + " , i = "+i);
        }
    }

}
