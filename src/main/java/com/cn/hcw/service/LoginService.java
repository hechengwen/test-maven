package com.cn.hcw.service;

import com.cn.hcw.restful.RestData;

/**
 * Created by lenovo on 2017/3/30 0030.
 */
public interface LoginService {
    RestData userLogin(String loginName,String password);
}
