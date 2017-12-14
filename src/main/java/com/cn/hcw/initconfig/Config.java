package com.cn.hcw.initconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by lenovo on 2017/3/16 0016.
 */
@Data
@XmlRootElement(name = "Config")
@XmlAccessorType(XmlAccessType.FIELD)//映射这个类中的所有字段到XML
@NoArgsConstructor
@AllArgsConstructor
public class Config implements Serializable {

    private static final long serialVersionUID = 20170316001L;
    @XmlElement(required = true)
    private String url;
    @XmlElement(required = true)
    private String data;
    @XmlElement(required = true)
    private String size;
}
