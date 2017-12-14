package com.cn.hcw.redis;

import java.util.List;
import java.util.Set;

/**
 * Created by lenovo on 2017/3/16 0016.
 */
public interface RedisService {

    /**
     * 通过key删除（字节）
     * @param key
     */
    public void del(byte[] key);
    /**
     * 通过key删除
     * @param key
     */
    public void del(String key);
    /**
     * 添加key value 并且设置存活时间(byte)
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(byte[] key, byte[] value, int liveTime);
    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, int liveTime);
    /**
     * 添加key value 并且设置存活时间
     * 注:若key已经存在了，则不设置, 若key不存在, 则设置。
     * @param key
     * @param value
     * @param liveTime
     */
    public void setIfEmpty(String key, String value, int liveTime);
    /**
     * 添加key value
     * @param key
     * @param value
     */
    public void set(String key, String value);
    /**
     * 添加key value (字节)(序列化)
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value);
    /**
     * 获取redis value (String)
     * @param key
     * @return
     */
    public String get(String key);
    /**
     * 获取redis value (byte [] )(反序列化)
     * @param key
     * @return
     */
    public byte[] get(byte[] key);
    /**
     * 通过正则匹配keys
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern);
    /**
     * 检查key是否已经存在
     * @param key
     * @return
     */
    public boolean exists(String key);
    /**
     * 清空redis 所有数据
     * @return
     */
    public String flushDB();/**
     /** 查看redis里有多少数据
     * @return
     */
    public long dbSize();
    /**
     * 添加list
     * @param key
     * @param value
     */
    public void setList(String key, String... value);
    /**
     * 删除list中一个key
     * @param key
     */
    public String delList(String key);
    /**
     * 获取redis value (String)
     * @param key
     * @return
     */
    public List<String> getList(String key);
    /**
     * 获取redis value (String)
     * @param key start end
     * @return
     */
    public List<String> getListByPage(String key,int start,int end);
    /**
     * 通过key 修改 第几个要素
     *
     * @param key
     */
    public String setListSub(String key, String value);
    /**
     * 获取redis listsize
     * @param key
     * @return
     */
    public List<String> getListSize(String key);
    /**
     * 减用户的摇奖次数
     * @param key
     */
    public long decr(String key);
    /**
     * 向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序
     * */
    public void zadd(String key,double score,String member);
    /**
     * 删除名称为key的zset中的元素member
     * */
    public void zrem(String key,String member);
    /**
     * 返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
     * */
    public Set zrange(String key,long start,long end);
    /**
     * 将 key 中储存的数字值增一
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key
     * @author haojunfu
     * @return
     */
    public Long getIncr (String key);

    boolean sismember(String key, String member);

    /**
     *
     * @param key
     * @param liveTime 过期时长（单位：秒）
     * @param members
     * @return
     */
    Long sadd(String key, int liveTime, String... members);

    Long sadd(String key, String... members);

    Long srem(String key, String... members);
}
