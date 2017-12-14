package com.rmbbox.generator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lxl on 15/9/23.
 *
 * 数据库配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBConfig {

    private String driver;
    private String url;
    private String username;
    private String password;


}
