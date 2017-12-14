package com.cn.hcw.task;

import com.cn.hcw.entity.CoreUser;
import com.cn.hcw.service.CoreUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hechengwen on 2017/3/19 0019.
 */
@Component
@Slf4j
public class TestTask {
    @Autowired
    CoreUserService coreUserService;
    @Autowired
    MongoTemplate mongoTemplate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @Scheduled(cron = "0 * * * * ?")
    public void testTask() {
        try {
            TimeUnit.SECONDS.sleep(10);
            log.info("[time={}],0s Task", sdf.format(new Date()));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

//    @Scheduled(cron = "3 * * * * ?")
    public void testTask1() {
        log.info("[time={}],3s Task", sdf.format(new Date()));
        CoreUser coreUser = new CoreUser();
        String loginName = new Date().getTime()+"";
        coreUser.setLoginName(loginName);
        coreUser.setPassword(new Date().getTime()+"");
        mongoTemplate.save(coreUser,"user");
        coreUserService.insert(coreUser);
        log.info("user insert success,loginName={}",loginName);
    }
}
