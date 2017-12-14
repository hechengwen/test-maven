package com.cn.hcw.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/6/30 0030
 * Description:
 * Others:
 */
public class WechatSendMessage {

    private static final String URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 返回数据格式：{
     "errcode":0,
     "errmsg":"ok",
     "msgid":200228332
     }
     * @param url
     * @param data
     * desc:当参数为json格式的字符串的时候，参数这样传
     * @return
     */
    public String sendJsonString(String url,String data){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(data,"UTF-8");
        post.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(post);
            int resultCode = response.getStatusLine().getStatusCode();
            if(HttpStatus.SC_OK != resultCode){
                post.abort();
                return null;
            } else {
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity);
                return result;
            }
        } catch (IOException io){
            post.abort();
        }
        return null;
    }

    /**
     *
     * @param jsonObject
     * @return
     * @throws UnsupportedEncodingException
     * desc:当请求的接口接收的参数为具体的参数时，例如：
     * public String receive(String mobile,String password){}
     * 发送请求格式：
     * jsonObject.put("mobile","17710363894");
     * jsonObject.put("password","666666");
     */
    public String send(JSONObject jsonObject) throws UnsupportedEncodingException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Map.Entry entry : jsonObject.entrySet()){
            list.add(new BasicNameValuePair((String)entry.getKey(),(String)entry.getValue()));
        }
        HttpPost post = new HttpPost("");
        post.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
        try {
            HttpResponse response = httpClient.execute(post);
            int a = response.getStatusLine().getStatusCode();
            if (a != 200) {
                post.abort();
                return null;
            }
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
