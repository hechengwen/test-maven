package com.cn.hcw.redis;

import com.cn.hcw.base.BaseService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.*;

/**
 * Created by hcw on 16/03/2017.
 *
 */
@Component
public class RedisHelper extends BaseService implements RedisService{

    /**
     * 获取一个jedis 客户端
     *
     * @return
     */
    @Autowired
    protected JedisPool jedisPool;

    /**
     * 通过key删除（字节）
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.del(key);
        }catch (Exception e){
            logger.error("del key"+key,e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间(byte)
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime) {
        this.set(key, value);
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value 并且设置存活时间
     *
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime) {
        this.set(key, value);
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 该方法设置的key和value, 若key已经存在了，则不设置, 若key不存在, 则设置。
     * @param key
     * @param value
     * @param liveTime
     */
    public void setIfEmpty(String key, String value, int liveTime) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.msetnx(key, value);
            jedis.expire(key, liveTime);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        String  a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 获取redis value (byte [] )(反序列化)
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = null;
        byte[]  a = null;
        try {
            jedis= jedisPool.getResource();
            a = jedis.get(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 通过正则匹配keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        Set<String>  a = null;
        try {
            jedis= jedisPool.getResource();
            a = jedis.keys(pattern);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        boolean a = false;
        try {
            jedis= jedisPool.getResource();
            a = jedis.exists(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    public String flushDB() {
        Jedis jedis = null;
        String a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.flushDB();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 查看redis里有多少数据
     */
    public long dbSize() {

//        return this.getJedis().dbSize();
        Jedis jedis = null;
        long a = 0;
        try {
            jedis= jedisPool.getResource();
            a = jedis.dbSize();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 检查是否连接成功
     *
     * @return
     */
    public String ping() {
        Jedis jedis = null;
        String a = "";
        try {
            jedis= jedisPool.getResource();
            a = jedis.ping();
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public void release(Jedis jedis, boolean isBroken) {
        if (jedis != null) {
            if (isBroken) {
                jedisPool.returnBrokenResource(jedis);
            } else {
                jedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 添加key value (字节)(序列化)
     *
     * @param key
     * @param value
     */
    public void setList(String key, String... value){
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.rpush(key, value);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 减用户的摇奖次数
     *
     * @param key
     */
    public long decr(String key){
        Jedis jedis = null;
        long re = 0;
        try {
            jedis= jedisPool.getResource();
            re = jedis.decr(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return re;
    }


    public void zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.zadd(key, score, member);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }


    public void zrem(String key, String member) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            jedis.zrem(key,member);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Set zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis= jedisPool.getResource();
            return jedis.zrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return new HashSet();
    }

    /**
     * 通过key删除
     *
     * @param key
     */
    public String delList(String key) {
        Jedis jedis = null;
        String redis = "";
        try {
            jedis= jedisPool.getResource();
//            System.out.print("!!!!!!!!!!!!!!!!!!"+key);
            redis = jedis.lpop(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return redis;
    }

    /**
     * 通过key 修改 第几个要素
     *
     * @param key
     */
    public String setListSub(String key,String value) {
        Jedis jedis = null;
        String redis = "";
        try {
            jedis= jedisPool.getResource();
            redis = jedis.lset(key, Long.valueOf(value), "0.00000");
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return "";
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getList(String key){
        Jedis jedis = null;
        boolean isBroken = false;
        List<String> lists = null;
        try {
            jedis= jedisPool.getResource();
            lists =  jedis.lrange(key, 0, -1);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return lists;
    }

    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListByPage(String key,int start,int end){
        Jedis jedis = null;
        boolean isBroken = false;
        List<String> lists = null;
        try {
            jedis= jedisPool.getResource();
            lists =  jedis.lrange(key,start,end);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return lists;
    }
    /**
     * 通过key获取
     *
     * @param key
     */
    public List<String> getListSize(String key){
        Jedis jedis = null;
        List<String> listString =  null;
        boolean isBroken = false;
        long listSize = 0;
        try {
            if (jedis==null){
                jedis= jedisPool.getResource();
            }
            listString = new ArrayList<String>();
            for (int i = 1; i < 15; i++) {
                listSize = jedis.llen(String.valueOf(i));
                listString.add(String.valueOf(listSize));
            }
        }catch (Exception e){
            logger.error("",e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return listString;
    }
    /**
     * 将 key 中储存的数字值增一
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @return
     */
    public Long getIncr (String key){
        Jedis jedis = null;
        Long  a = null;
        try {
            jedis= jedisPool.getResource();
            a = jedis.incr(key);
        }catch (Exception e){
            logger.error("", e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
        }finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
        return a;
    }

    public boolean sismember(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, member);
        } catch (Throwable e) {
            logger.error(key+"---"+member, e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
            return false;
        } finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long sadd(String key, int liveTime, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long a = jedis.sadd(key, members);
            //大于0时，设置过期
            if(liveTime>0){
                jedis.expire(key, liveTime);
            }
            return a;
        } catch (Throwable e) {
            logger.error("Redis Key: "+key, e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
            return -1l;
        } finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long sadd(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long a = jedis.sadd(key, members);
            return a;
        } catch (Throwable e) {
            logger.error("Redis Key: "+key, e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
            return -1l;
        } finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

    public Long srem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key, members);
        } catch (Throwable e) {
            logger.error("Redis Key: "+key, e);
            if(jedis!=null){
                jedisPool.returnBrokenResource(jedis);
                jedis = null;
            }
            return -1l;
        } finally {
            if(jedis != null ) {
                jedisPool.returnResource(jedis);
            }
        }
    }

}
