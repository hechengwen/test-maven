package com.cn.hcw.redis;

import com.cn.hcw.restful.RestData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/17 0017.
 */
@Component
public class CacheManagerService {

    @Autowired
    RedisService redisService;

    protected Logger logger;

    private static StringRedisSerializer stringRedisSerializer;

    @PostConstruct
    public void init() {
        stringRedisSerializer=new StringRedisSerializer();
    }


    /**
     * 通过key删除（字节）
     *
     * @param key
     */
    public void del(byte[] key) {
            redisService.del(key);
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
            redisService.del(key);

    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime) {


            redisService.set(key, value, liveTime);

    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
            redisService.set(key, value, liveTime);
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * 注:若key已经存在了，则不设置, 若key不存在, 则设置。
     * @param key
     * @param value
     * @param liveTime
     */
    public void setIfEmpty(String key, String value, int liveTime) {
            redisService.setIfEmpty(key, value, liveTime);
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
            redisService.set(key, value);
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
            redisService.set(key, value);
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {

        return redisService.get(key);
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        return redisService.get(key);
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisService.keys(pattern);
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisService.exists(key);
    }

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    public String clear() {
        return redisService.flushDB();
    }

    /**
     * 查看redis里有多少数据
     */
    public long size() {

        return redisService.dbSize();
    }

    public void setObject(String key, Object value){
            try {
                byte[] bv = serialize(value);
                byte[] bk = stringRedisSerializer.serialize(key);

                set(bk, bv);
            }
            catch(Exception ex){
                logger.error("Error ser", ex);
            }
    }

    public void setObject(String key, Object value, int liveTime){
            try {
                byte[] bv = serialize(value);
//                byte[] bk = serialize(key);
                byte[] bk = stringRedisSerializer.serialize(key);

                set(bk, bv, liveTime);
            }
            catch(Exception ex){
                logger.error("Error ser", ex);
            }
    }

    public void delObject(String key){
            byte[] bk = stringRedisSerializer.serialize(key);
            del(bk);

    }

    public Object getObject(String key){
        try {
//            byte[] bk = serialize(key);
            byte[] bk = stringRedisSerializer.serialize(key);
            byte[] b = get(bk);
            if(b==null)
                return null;
            return deserialize(b);
        }
        catch(Exception ex){
            logger.error("Error ser", ex);
        }
        return null;
    }

    private byte[] serialize(Object o) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //构造一个字节输出流
        ObjectOutputStream oos = new ObjectOutputStream(baos); //构造一个类输出流
        //oos.writeObject(list); //写这个对象
        oos.writeObject(o); //写这个对象
        byte[] buf = baos.toByteArray(); //从这个地层字节流中把传输的数组给一个新的数组
        oos.flush();
        return buf;
    }

    private Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException{
        //构建一个类输入流，用字节输入流实现
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        return obj;
    }

    /**
     * 减用户的摇奖次数
     *
     * @param key
     */
    public long decr(String key) {
        return redisService.decr(key);
    }
    public void setList(String key, String... value)throws Exception{
        redisService.setList(key, value);
    }
    public String delList(String key)throws Exception{
        return redisService.delList(key);
    }
    public String setListSub(String key,String value)throws Exception{
        return redisService.setListSub(key,value);
    }
    public List<String> getList(String key)throws Exception {
        return redisService.getList(key);
    }
    public List<String> getListByPage(String key,int start,int end)throws Exception {
        return redisService.getListByPage(key,start,end);
    }


    public synchronized List<String> getListByListSize(String key) throws Exception{
        List<String> lists = redisService.getList(key);
        if (lists==null||lists.size()<1){
//            initRedis();
        }
        List<String> listString = new ArrayList();
        listString = redisService.getListSize("");
        return listString;
    }


    /**
     * 重复操作时间限制
     * @param key  redis的key
     * @param oprDesc   操作说明,比如投资、赎回
     * @param timeLimit 时间限制 秒
     * @return
     */
    public RestData repeatOperationLimit(String key, String oprDesc, int timeLimit) {
        if (StringUtils.isBlank(oprDesc)) {
            oprDesc = "";
        }

        RestData restData = new RestData();
        String cache = this.get(key);
        if (cache != null) {
            Long time = Long.valueOf(cache) + 1000 * timeLimit;
            long now = System.currentTimeMillis();
            long diff = time - now;
            if(diff <= 0){
                this.del(key);
                restData.setSuccess(1);
                return restData;
            }
            if (diff < 1000*60) {
                restData.setComment("您的" + oprDesc + "操作过于频繁，请过" + diff/1000 + "秒后再试");
            } else {
                restData.setComment("您的" + oprDesc + "操作过于频繁，请过" + diff/1000/60 + "分" + (diff/1000 - (diff/1000/60)*60) + "秒后再试");
            }

            return restData;
        }

        this.setIfEmpty(key, String.valueOf(System.currentTimeMillis()), timeLimit);

        restData.setSuccess(1);
        return restData;
    }
}
