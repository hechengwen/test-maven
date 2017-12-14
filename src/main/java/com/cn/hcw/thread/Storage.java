package com.cn.hcw.thread;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/4 0004
 * Description:仓库类Storage实现缓冲区
 * Others:
 */
public class Storage {
    // 仓库最大存储量
    private final int MAX_SIZE = 1000;

    private int psum = 0;
    private int csum = 0;

    private Map<String,String> pmap = new HashMap();
    private Map<String,String> cmap = new HashMap();

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    public void produce(int num) {
        synchronized (list) {
            while (list.size() + num > MAX_SIZE) {
                if (!cmap.containsKey(Thread.currentThread().getName())) {
                    cmap.put(Thread.currentThread().getName(),"");
                    csum = csum + num;
                }
                System.out.println("线程名：【" + Thread.currentThread().getName() +  "】，【要生产的产品数量】:" + num + ",【库存量】:" + list.size() + ",暂时不能执行生产任务!!待生产总数：" + csum);
                try {
                    // 由于条件不满足，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 生产条件满足情况下，生产num个产品
            for (int i = 1; i <= num; ++i) {
                list.add(new Object());
            }

            csum = csum - num;

            if (csum <0 ){
                csum=0;
            }
            System.out.println("线程名：【" + Thread.currentThread().getName() +  "】，【已经生产产品数】:" + num + ",【现仓储量为】:" + list.size()+",待生产总数：" + csum);
            list.notifyAll();
        }
    }

    // 消费num个产品
    public void consume(int num) {
        // 同步代码段
        synchronized (list) {
            // 如果仓库存储量不足
            while (list.size() < num) {
                if (!pmap.containsKey(Thread.currentThread().getName())){
                    pmap.put(Thread.currentThread().getName(),"");
                    psum = num + psum;
                }
                System.out.println("线程名：【" + Thread.currentThread().getName() +  "】，【要消费的产品数量】:" + num + ",【库存量】:" + list.size() + ",暂时不能执行生产任务!待消费总数：" + psum);
                try {
                    // 由于条件不满足，消费阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 消费条件满足情况下，消费num个产品
            for (int i = 1; i <= num; ++i) {
                list.remove();
            }
            psum = psum-num;
            if (psum < 0) {
                psum = 0;
            }
            System.out.println("线程名：【" + Thread.currentThread().getName() +  "】，【已经消费产品数】:" + num + ",【现仓储量为】:" + list.size()+",待消费总数：" + psum);

            list.notifyAll();
        }
    }
}
