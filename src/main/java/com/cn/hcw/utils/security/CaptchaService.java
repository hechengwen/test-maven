package com.cn.hcw.utils.security;

import com.cn.hcw.redis.CacheManagerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，Beijing Zipi Wealth Network Technology Co., Ltd.
 * Author: hechengwen
 * Version: 1.0
 * Date: 2017/4/24 0024
 * Description:生成验证码
 * Others:
 */
@Component
public class CaptchaService {
    @Autowired
    CacheManagerService cacheManagerService;
    /**
     * 生成验证码
     * 若已经存在验证码不需要重新生成,若不存在需要重新生成并保存至redis
     * @param mobile
     * @param type
     * @return
     */
    private String randomCaptchaByType(String mobile, String type) {

        String captcha = cacheManagerService.get(this.getCaptchaKey(mobile, type));

        // 若存在,则不需要重新生成验证码, 若不存在,需要重新生成
        if (StringUtils.isNotEmpty(captcha)) {
            return captcha;
        }

        // 生成验证码
        captcha = RandomStringUtils.randomNumeric(6);
        // 保存至redis 有效期10分钟
        cacheManagerService.set(this.getCaptchaKey(mobile, type), captcha, 10 * 60);

        return captcha;
    }

    /**
     * 获取验证码key 规则:mobilecaptcha:184xxxxxxxx|CONFIRM_CREDITMARKET_REGISTER
     * @param mobile
     * @param type
     * @return
     */
    private String getCaptchaKey(String mobile, String type) {
        return "mobilecaptcha:" + mobile + "|" + type;
    }
}
