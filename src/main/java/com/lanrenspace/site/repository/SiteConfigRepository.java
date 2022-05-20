package com.lanrenspace.site.repository;

import com.lanrenspace.site.entity.SiteConfig;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public interface SiteConfigRepository extends ReactiveCrudRepository<SiteConfig, Long> {
}
