import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.hcw.dao.CoreUserMapper;
import com.cn.hcw.dao.StudentMapper;
import com.cn.hcw.entity.CoreUser;
import com.cn.hcw.initconfig.initXML;
import com.cn.hcw.redis.RedisService;
import com.cn.hcw.service.CoreUserService;
import com.cn.hcw.utils.LotteryUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import redis.clients.jedis.Jedis;

import java.util.*;


/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/5/10 0010
 * Description:
 * Others:
 */
@Slf4j
@ContextConfiguration("classpath:application.xml")
@ActiveProfiles("test")
public class CoreUserTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CoreUserService coreUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private CoreUserMapper coreUserDao;

//    @Autowired
//    private MongoTemplate mongoTemplate;

//    @Ignore
    @Test
    public void equals(){
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CoreUserMapper userDao1 = (CoreUserMapper) appContext.getBean("coreUserMapper");
        CoreUserMapper userDao2 = (CoreUserMapper) appContext.getBean("coreUserMapper");
        CoreUser cc1 = userDao1.selectByPrimaryKey("1");
        CoreUser cc2 = userDao2.selectByPrimaryKey("1");
        CoreUser cc3 = coreUserDao.selectByPrimaryKey("1");
        System.out.println(coreUserDao==userDao1); // false, why? 不是单例的么?
        System.out.println(userDao2==userDao1); // ture
    }

    @Test
    public void test(){
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:applicationContext-mongo.xml");
        MongoTemplate mongoTemplate = (MongoTemplate) appContext.getBean("mongoTemplate");
        mongoTemplate.save(new CoreUser("100011","17710937"),"user");
    }


    @Test
    public void coreUserByUserId(){
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:application.xml");
        CoreUserMapper coreUserDao1 = (CoreUserMapper) appContext.getBean("coreUserMapper");
        initXML initBean = (initXML) appContext.getBean("initXML");
        PropertyPlaceholderConfigurer ppc = (PropertyPlaceholderConfigurer) appContext.getBean("propertyConfigurer");
        DataSourceTransactionManager dstm = (DataSourceTransactionManager) appContext.getBean("transactionManager");
        CoreUser cc = coreUserDao.selectByPrimaryKey("1");
        CoreUser cc1 = coreUserDao1.selectByPrimaryKey("1");
        CoreUser coreUser = null;
        try {
            coreUser = coreUserService.getUserById("1");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info(coreUser.getLoginName());
        initBean.init();
    }

    @Test
    public void testCache(){
        redisService.set("key1","value1",60);
        String value1 = redisService.get("key1");
    }

    @Test
    public void testJson(){
        CoreUser coreUser = new CoreUser("zhangsan","123958");
        String jsonString = JSON.toJSONString(coreUser);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.err.println("jsonString ="+jsonString+jsonObject.get("name"));
        System.out.println(jsonObject);
        jsonObject.get("name");
    }

    /**
     * 抽奖测试
     */
    @Test
    public void lottery(){
        List<Lottery> lotteries = new ArrayList<Lottery>();
        List<Double> doubleList = new ArrayList<Double>();
        Lottery lottery1 = new Lottery(0.21d,"气球球");
        Lottery lottery2 = new Lottery(0.31d,"篮球球");
        Lottery lottery3 = new Lottery(0.12d,"乒乓球球");
        Lottery lottery4 = new Lottery(0.36d,"羽毛球球");
        lotteries.add(lottery1);lotteries.add(lottery2);lotteries.add(lottery3);lotteries.add(lottery4);
        for (Lottery lottery : lotteries){
            doubleList.add(lottery.getChances());
        }
        int a = LotteryUtil.lottery(doubleList);
        int b = LotteryUtil.lottery(doubleList);
        int c = LotteryUtil.lottery(doubleList);
        int d = LotteryUtil.lottery(doubleList);
        log.info("lottery a== " + lotteries.get(a) + ",lottery b== " + lotteries.get(b) + ",lottery c== " + lotteries.get(c) + ",lottery d== " + lotteries.get(d));
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    class Lottery{
        private Double chances;
        private String name;
    }

    @Test
    public void redis(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
        //设置 redis 字符串数据
        jedis.set("runoobkey", "Redis tutorial");
        // 获取存储的数据并输出
        System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));
        //存储数据到列表中
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);
        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }
        // 获取数据并输出
        Set<String> stringList = jedis.keys("*");
        Iterator iterator = stringList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Autowired
    StudentMapper studentMapper;
    @Test
    public void mybatis(){
        List list = new ArrayList();
        list.add("zs");
        list.add("lisi");
        String[] s = {"zs","lisi"};
        Map map = new HashMap();
        map.put("sex","男");
        int[] i = {19,20};
        map.put("ages",i);
        int i1 = studentMapper.countByArray(s);
        int i2 = studentMapper.countByList(list);
        int i3 = studentMapper.countByMap(map);
        log.info("count == "+i1+i2+i3);
    }


}
