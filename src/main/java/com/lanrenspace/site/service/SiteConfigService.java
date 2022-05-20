package com.lanrenspace.site.service;

import com.alibaba.fastjson.JSONObject;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public interface SiteConfigService {

    /**
     * 获取最新配置
     *
     * @return
     */
    Mono<JSONObject> getLatestContent();
}
