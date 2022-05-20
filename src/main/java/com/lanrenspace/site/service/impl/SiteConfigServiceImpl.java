package com.lanrenspace.site.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lanrenspace.site.entity.SiteConfig;
import com.lanrenspace.site.exceptions.ConfigException;
import com.lanrenspace.site.exceptions.ExceptionDef;
import com.lanrenspace.site.exceptions.ServiceException;
import com.lanrenspace.site.service.SiteConfigService;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class SiteConfigServiceImpl implements SiteConfigService {

    private final R2dbcEntityTemplate entityTemplate;

    public SiteConfigServiceImpl(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    /**
     * 获取最新配置
     *
     * @return
     */
    @Override
    public Mono<JSONObject> getLatestContent() {
        return entityTemplate.select(SiteConfig.class)
                .matching(Query.empty().sort(Sort.by(Sort.Order.desc("version"))))
                .first()
                .switchIfEmpty(Mono.error(new ConfigException(ExceptionDef.CONFIG_DATA_IS_NULL)))
                .map(siteConfig -> {
                    if (ObjectUtils.isEmpty(siteConfig.getContent())) {
                        throw new ConfigException(ExceptionDef.CONFIG_DATA_IS_NULL);
                    }
                    return JSONObject.parseObject(siteConfig.getContent());
                });
    }
}
