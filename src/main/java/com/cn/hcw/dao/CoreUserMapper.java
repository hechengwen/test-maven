package com.cn.hcw.dao;

import com.cn.hcw.entity.CoreUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/3/9 0009.
 */
@Repository
public interface CoreUserMapper {
    CoreUser selectByPrimaryKey(@Param("userId") String userId);

    int insert(CoreUser coreUser);
}
