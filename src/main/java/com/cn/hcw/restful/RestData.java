package com.cn.hcw.restful;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/3/17 0017.
 */
@Data
@NoArgsConstructor
public class RestData implements Serializable {
    private int success = 0; // 默认为0,失败 1成功

    private String code = ""; // 返回信息码

    private String comment; // 说明

    private Object data;    // 数据, 列表等数据放到这

    public RestData(int success, String comment, Object data) {
        this.success = success;
        this.code = "";
        this.comment = comment;
        this.data = data;
    }

    public RestData(int success, String code, String comment, Object data) {
        this.success = success;
        this.code = code;
        this.comment = comment;
        this.data = data;
    }
}
