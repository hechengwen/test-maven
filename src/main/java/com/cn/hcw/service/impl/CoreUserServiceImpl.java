package com.cn.hcw.service.impl;

import com.cn.hcw.dao.CoreUserMapper;
import com.cn.hcw.entity.CoreUser;
import com.cn.hcw.redis.CacheManagerService;
import com.cn.hcw.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/5/10 0010
 * Description:
 * Others:
 */
@Service
public class CoreUserServiceImpl implements CoreUserService{

    @Autowired
    private CoreUserMapper coreUserDao;

    @Autowired
    CacheManagerService cacheManagerService;

    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    @Override
    public CoreUser getUserById(String userId){
        CoreUser coreUser = null;
        Object key = cacheManagerService.getObject("key");
        if(null == key){
            coreUser = coreUserDao.selectByPrimaryKey(userId);
            coreUser.setAddress("beijing");
            cacheManagerService.setObject("key",coreUser,30);
        } else coreUser = (CoreUser) key;
        System.out.println("userId = " + userId);
        return coreUser;
    }

    public int insert(CoreUser coreUser){
        coreUserDao.insert(coreUser);
        return 1;
    }

}
