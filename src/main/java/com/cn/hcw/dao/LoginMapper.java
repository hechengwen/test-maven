package com.cn.hcw.dao;

import com.cn.hcw.entity.CoreUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/3/30 0030.
 */
@Repository
public interface LoginMapper {
    CoreUser userLogin(@Param("loginName")String loginName,@Param("password")String password);
}
