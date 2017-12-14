package com.cn.hcw.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.hcw.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import sun.management.Agent;

import java.util.*;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/5/15 0015
 * Description:
 * Others:
 */
@Slf4j
public class JacksonTester {


    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args){

        /**
         * json——>对象
         */
        String jsonString = "[{\"name\":\"Mahesh\", \"age\":21},{\"name\":\"feifei\",\"age\":20}]";
        try {
            // 将json字符串和对象类型作为参数，（反序列化json到对象）
            Student[] student = mapper.readValue(jsonString,Student[].class);
            System.out.println("student1 : " + student[0]);
            System.out.println("student2 : " + student[1]);
            mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            // 序列化对象到json
            jsonString = mapper.writeValueAsString(student);
            System.out.println(jsonString);
        } catch (Exception e){
            log.info(e.getMessage());
        }

//        readJson2List();
        jsonToObject();
//        ObjectToJson();
//        objectToJsonArray();
//        jsonArrayToObject();
//        objectToString();
        stringToObject();


    }

    /**
     * json——>list
     */
    public static void readJson2List(){
        String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
                + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";

        try {
            List<LinkedHashMap<String,Object>> list = mapper.readValue(json, List.class);
            /*for (int i = 0 ; i <= list.size(); i++){
                LinkedHashMap<String,Object> map = list.get(i);
                Set<String> set = map.keySet();
                Iterator<String> iter = set.iterator();
                while (iter.hasNext()){
                    String key = iter.next();
                    System.out.println("key = " + key + ", value = " + map.get(key));
                }
            }*/
            for (LinkedHashMap<String,Object> map :list) {
                for (Map.Entry entry : map.entrySet()){
                    System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
                }
            }
        } catch (Exception e){
            log.info(e.getMessage());
        }
    }

    public static void jsonToObject(){
        String json = "{\"id\":\"104052\",\"name\":\"xiaofei\"}";
        JSONObject object = JSON.parseObject(json);
        String id = (String) object.get("id");
        System.out.println("id = " + id);

        WeiBo w = JSON.parseObject(json,WeiBo.class);
        System.out.println("jsonToObject ——> " + w);
    }

    public static void ObjectToJson(){
        WeiBo weiBo = new WeiBo("104052","hechengwen");
        JSONObject jsonObject = (JSONObject) JSON.toJSON(weiBo);
        System.out.println("ObjectToJson ——> " +jsonObject.get("id"));
    }

    public static void objectToJsonArray(){
        String json = "[{\"id\":\"104052\",\"name\":\"xiaofei\"},{\"id\":\"104088\",\"name\":\"xiaofeifei\"}]";
        JSONArray jsonArray = JSON.parseArray(json);
        System.out.println("objectToJsonArray ——> " +jsonArray.get(1));
    }

    public static void jsonArrayToObject(){
        String json = "[{\"id\":\"104052\",\"name\":\"xiaofei\"},{\"id\":\"104088\",\"name\":\"xiaofeifei\"}]";
        List<WeiBo> list = JSON.parseArray(json,WeiBo.class);
        for (WeiBo weiBo : list){
            System.out.println("jsonArrayToObject ——> " +weiBo);
        }
    }

    public static void objectToString(){
        WeiBo weiBo = new WeiBo("109889","dalaoban");
        String s = JSON.toJSONString(weiBo);
        System.out.println("objectToString ——> " +s);
    }

    public static void stringToObject(){
        String json = "{\"id\":\"019879999839912\",\"name\":\"oldboss\",\"xiaoBoBoList\":[{\"age\":12,\"name\":\"newboss\",\"rate\":\"0.8\"},{\"age\":18,\"name\":\"xiaonewboss\",\"rate\":\"1.8\"}]}";
        WeiBo w = JSON.parseObject(json,WeiBo.class);
        for (XiaoBoBo xiaoBoBo :w.getXiaoBoBoList()) {
            System.out.println("age = " + xiaoBoBo.getAge() + ",name = " + xiaoBoBo.getName() + ",rate = " + xiaoBoBo.getRate());
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class WeiBo{
        private String id;
        private String name;
        private List<XiaoBoBo> xiaoBoBoList;
        public WeiBo(String id,String name){
            this.id = id;
            this.name = name;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class XiaoBoBo{
        private int age;
        private String name;
        private String rate;
    }

}
