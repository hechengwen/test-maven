package com.cn.hcw.service;

import com.cn.hcw.entity.CoreUser;

/**
 * Created by lenovo on 2017/3/9 0009.
 */
public interface CoreUserService {
    CoreUser getUserById(String userId);

    int insert(CoreUser coreUser);
}
