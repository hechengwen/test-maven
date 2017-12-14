package com.cn.hcw.service.impl;

import com.cn.hcw.dao.LoginMapper;
import com.cn.hcw.dao.slave.LoginRMapper;
import com.cn.hcw.entity.CoreUser;
import com.cn.hcw.restful.RestData;
import com.cn.hcw.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hcw on 2017/3/30 0030.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    LoginRMapper loginRMapper;
    @Override
    public RestData userLogin(String loginName, String password){
        RestData restData = new RestData();
        CoreUser coreUser = loginMapper.userLogin(loginName,password);
        CoreUser coreUserR = loginRMapper.userLogin(loginName,password);
        System.err.println("userLogin used execute !!!");
        if (null == coreUser){
            restData.setSuccess(0);
            restData.setComment("user is not exist");
            return restData;
        }
        restData.setComment("login success");
        restData.setSuccess(1);
        return restData;
    }


}
