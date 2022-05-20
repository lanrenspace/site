package com.lanrenspace.site.config;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.DisposableWorkerIdAssigner;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

/**
 * @Author lanrenspace@163.com
 * @Description: baidu uid 集成
 **/
@Configuration
public class BaiduUidConfig {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public BaiduUidConfig(R2dbcEntityTemplate entityTemplate) {
        this.r2dbcEntityTemplate = entityTemplate;
    }

    /**
     * 使用baidu uid 的cached模式
     *
     * @return
     */
    @Bean
    public CachedUidGenerator cachedUidGenerator() {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner());
        cachedUidGenerator.setTimeBits(29);
        cachedUidGenerator.setWorkerBits(21);
        cachedUidGenerator.setSeqBits(13);
        cachedUidGenerator.setEpochStr("2022-05-01");

        // RingBuffer size扩容参数, 可提高UID生成的吞吐量
        cachedUidGenerator.setBoostPower(3);
        // 另外一种RingBuffer填充时机, 在Schedule线程中, 周期性检查填充
        cachedUidGenerator.setScheduleInterval(60);

        return cachedUidGenerator;
    }


    @Bean
    public WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner(r2dbcEntityTemplate);
    }
}
