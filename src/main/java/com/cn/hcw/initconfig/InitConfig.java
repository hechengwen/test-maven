package com.cn.hcw.initconfig;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/3/16 0016.
 */
@Component
@Slf4j
public class InitConfig {

    String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String appid = "wxcdff9c0dea180c91";

    private static final String appsecret = "f66093338c0d50e1f804c261bb3c3a62";

    @PostConstruct
    public void testInit(){
        log.info("注解初始化！！");
    }

    @PreDestroy
    public void testDestory(){
        log.info("注解destory！！");
    }

    public String send() throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile","17710363894");
        jsonObject.put("password","666666");
        HttpClient httpClient = HttpClientBuilder.create().build();
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

        for(Map.Entry entry :jsonObject.entrySet()){
            list.add(new BasicNameValuePair((String) entry.getKey(),(String) entry.getValue()));
        }

//        url = String.format(url, appid,appsecret);
        HttpPost httpPost = new HttpPost("http://127.0.0.1:8088/open/password/resetPassword");
        httpPost.setEntity(new UrlEncodedFormEntity(list,"UTF-8"));
//        StringEntity stringEntity = new StringEntity(jsonObject.toJSONString());
//        httpPost.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity,"UTF-8");
        //this.parseData(result);
        return result;
    }

    /**
     * 解析token数据
     * @param data
     * @return
     */
    private boolean parseData(String data){
        JSONObject jsonObject = JSONObject.parseObject(data);//将string（格式为key-value）转换成json对象
        String tokenName = "access_token";
        String expiresInName = "expires_in";
        try {
            String token = jsonObject.get(tokenName).toString();
            if(StringUtils.isBlank(token)){
                log.error("token获取失败,获取结果" + data);
                return false;
            }
            String expiresIn = jsonObject.get(expiresInName).toString();
            if(StringUtils.isBlank(expiresIn)){
                log.error("token获取失败,获取结果" + expiresIn);
                return false;
            }
        } catch (Exception e) {
            log.error("token 结果解析失败，token参数名称: " + tokenName
                    + "有效期参数名称:" + expiresInName
                    + "token请求结果:" + data);
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
