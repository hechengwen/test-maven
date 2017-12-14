package com.cn.hcw.thread;

import lombok.Data;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/8/4 0004
 * Description:
 * Others:
 */
@Data
public class Consumer extends Thread {

    // 每次消费的产品数量
    private int num;

    // 所在放置的仓库
    private Storage storage;

    // 构造函数，设置仓库
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.consume(num);
    }

}
