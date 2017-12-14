package com.cn.hcw.initconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/16 0016.
 */
@Component
@Slf4j
public class InitBean implements InitializingBean{

    protected static Map<String,Object> initMap = new HashMap();

    public void afterPropertiesSet() throws Exception{
        this.getConfig();
        //this.getEntity();
        initMap.put("config",this.getConfig());
        log.info("实现InitializingBean初始化！！");
    }

    public static Config getConfig() throws Exception{
        JAXBContext context = JAXBContext.newInstance(Config.class);
        Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("d:/var/zipi/wealth/Config.xml"))));
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Config config = (Config) unmarshaller.unmarshal(reader);
        return config;
    }

    /*public static void getEntity() throws Exception{
        JAXBContext context = JAXBContext.newInstance(Config.class);
        Marshaller marshaller = context.createMarshaller();
        //marshaller.marshal(new Config("www.baidu.com","data","99"), System.out);
        marshaller.marshal(new Config("www.baidu.com","data","99"),new FileOutputStream(new File("d:/var/zipi/wealth/produce.xml")));
        log.info("produce xml file success,path = d:/var/zipi/wealth/produce.xml");
    }*/

    public static Map<String,Object> getInitMap(){
        return initMap;
    }

}
