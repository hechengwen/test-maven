package com.cn.hcw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/3/9 0009.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreUser implements Serializable{

    private static final long serialVersionUID = 20170317L;

    private String userName;
    private String userMobile;
    private String userId;
    private String email;
    private String loginName;
    private String address;
    private String password;

    public CoreUser(String userName,String userMobile){
        this.userName = userName;
        this.userMobile = userMobile;
    }

}
