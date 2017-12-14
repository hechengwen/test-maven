package com.cn.hcw.rabbit;

/**
 * Created by lenovo on 2017/3/31 0031.
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //渠道
    private String channel;
    //来源
    private String content;

}
