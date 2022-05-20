package com.lanrenspace.site.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @Author lanrenspace@163.com
 * @Description: redis常用操作
 **/
public class RedisUtils {

    @Autowired
    private ReactiveRedisTemplate<String, Object> redisTemplate;


    /**
     * set
     *
     * @param key
     * @param value
     * @param seconds
     */
    public void set(String key, Object value, long seconds) {
        set(key, value, Duration.ofSeconds(seconds)).subscribe();
    }

    /**
     * set
     *  @param key
     * @param value
     * @param duration
     * @return
     */
    public Mono<Boolean> set(String key, Object value, Duration duration) {
        return redisTemplate.opsForValue().set(key, value, duration);
    }

    /**
     * set
     *  @param key
     * @param value
     * @param duration
     * @return
     */
    public void setNoN(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration).subscribe();
    }

    /**
     * set
     *
     * @param key
     * @param value
     */
    public void setNeverExpires(String key, Object value) {
        set(key, value, -1);
    }


    /**
     * 重置key的过期时间
     *
     * @param key
     * @param duration
     */
    public void setExpires(String key, Duration duration) {
        redisTemplate.expire(key, duration).subscribe();
    }


    /**
     * get
     *
     * @param key
     * @return
     */
    public Mono<Object> get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * del
     *
     * @param key
     * @return
     */
    public Mono<Long> del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * hasKey
     *
     * @param key
     * @return
     */
    public Mono<Boolean> hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
