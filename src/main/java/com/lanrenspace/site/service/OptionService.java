package com.lanrenspace.site.service;

import com.lanrenspace.site.base.IBaseBeanService;
import com.lanrenspace.site.entity.Option;
import com.lanrenspace.site.repository.OptionRepository;
import com.lanrenspace.site.vo.OptionInfoVO;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public interface OptionService extends IBaseBeanService<Option, Long, OptionRepository> {

    /**
     * 获取所有类别信息
     *
     * @param request
     * @return
     */
    Flux<OptionInfoVO> getOptions(ServerRequest request);


    /**
     * 创建类别信息
     *
     * @param request
     * @return
     */
    Mono<Option> createOption(ServerRequest request);
}
