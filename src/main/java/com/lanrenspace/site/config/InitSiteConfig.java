package com.lanrenspace.site.config;

import com.alibaba.fastjson.JSONObject;
import com.lanrenspace.site.constant.SysCont;
import com.lanrenspace.site.service.SiteConfigService;
import com.lanrenspace.site.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description: 启动完成后的初始化
 **/
@Slf4j
@Component
public class InitSiteConfig implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("load site init config start...");
        RedisUtils redisUtils = ApplicationContextHelper.getBean(RedisUtils.class);
        Mono<JSONObject> latestContent = ApplicationContextHelper.getBean(SiteConfigService.class).getLatestContent();
        latestContent.subscribe(jsonObject -> redisUtils.setNeverExpires(SysCont.SITE_CONFIG, jsonObject));
        log.info("load site init config end...");
    }
}
