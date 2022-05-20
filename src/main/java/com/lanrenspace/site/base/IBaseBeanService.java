package com.lanrenspace.site.base;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public interface IBaseBeanService<T extends DataEntity<ID>, ID, R extends ReactiveCrudRepository<T, ID>> {
    /**
     * 获取业务对象
     *
     * @return
     */
    Class<T> getBeanClass();

    /**
     * 获取UID生成器
     *
     * @return
     */
    UidGenerator getUidGenerator();


    /**
     * 获取ID
     *
     * @return
     */
    Long getId();


    /**
     * 获取EntityTemplate
     *
     * @return
     */
    R2dbcEntityTemplate getEntityTemplate();


    /**
     * 保存
     *
     * @param entity
     * @return
     */
    Mono<T> save(T entity);
}
