package com.lanrenspace.site.base;

import com.baidu.fsg.uid.UidGenerator;
import com.lanrenspace.site.config.ApplicationContextHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Slf4j
public class BaseBeanServiceImpl<T extends DataEntity<ID>, ID, R extends ReactiveCrudRepository<T, ID>> implements InitializingBean, IBaseBeanService<T, ID, R> {
    /**
     * 业务对象
     */
    private Class<T> beanClass;

    private UidGenerator uidGenerator;

    private R2dbcEntityTemplate r2dbcEntityTemplate;

    /**
     * 获取业务对象
     *
     * @return
     */
    @Override
    public Class<T> getBeanClass() {
        return this.beanClass;
    }

    /**
     * 获取UID生成器
     *
     * @return
     */
    @Override
    public UidGenerator getUidGenerator() {
        return this.uidGenerator;
    }

    /**
     * 获取ID
     *
     * @return
     */
    @Override
    public Long getId() {
        return this.uidGenerator.getUID();
    }

    /**
     * 获取EntityTemplate
     *
     * @return
     */
    @Override
    public R2dbcEntityTemplate getEntityTemplate() {
        return this.r2dbcEntityTemplate;
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    public Mono<T> save(T entity) {
        if (ObjectUtils.isEmpty(entity.getId())) {
            entity.setId(uidGenerator.getUID());
            entity.setCreateTime(LocalDateTime.now());
            return this.r2dbcEntityTemplate.insert(entity);
        } else {
            entity.setUpdateTime(LocalDateTime.now());
            return this.r2dbcEntityTemplate.update(entity);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if ((getClass().getGenericSuperclass() instanceof ParameterizedType)) {
            Type[] typeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            this.beanClass = ((Class<T>) typeArguments[0]);
        }
        this.uidGenerator = ApplicationContextHelper.getBean(UidGenerator.class);
        this.r2dbcEntityTemplate = ApplicationContextHelper.getBean(R2dbcEntityTemplate.class);
    }
}
