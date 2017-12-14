package com.cn.hcw.dao.slave;

import com.cn.hcw.entity.CoreUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2017/3/30 0030.
 */
@Repository
public interface LoginRMapper {
    CoreUser userLogin(@Param("loginName") String loginName, @Param("password") String password);
}
