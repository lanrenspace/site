package com.lanrenspace.site.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description: 站点开放接口
 **/
public interface OpenApiService {

    /**
     * 获取所有的类别
     *
     * @param request
     * @return
     */
    Mono<ServerResponse> getOptions(ServerRequest request);

    /**
     * 创建
     *
     * @param request
     * @return
     */
    Mono<ServerResponse> createOption(ServerRequest request);
}
