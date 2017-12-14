package com.cn.hcw.thread;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/3 0003
 * Description:
 * Others:
 */
public class TestThread {

    public static void main(String[] args){
        Storage storage = new Storage();

        Producer p1 = new Producer(storage);
        Producer p2 = new Producer(storage);
        Producer p3 = new Producer(storage);
        Producer p4 = new Producer(storage);

        Consumer c1 = new Consumer(storage);
        Consumer c2 = new Consumer(storage);
        Consumer c3 = new Consumer(storage);
        Consumer c4 = new Consumer(storage);
        Consumer c5 = new Consumer(storage);

        // 设置生产者产品生产数量
        p1.setNum(90);
        p2.setNum(10);
        p3.setNum(50);
        p4.setNum(20);

        // 设置消费者产品消费数量
        c1.setNum(120);
        c2.setNum(20);
        c3.setNum(10);
        c4.setNum(10);
        c5.setNum(10);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}
