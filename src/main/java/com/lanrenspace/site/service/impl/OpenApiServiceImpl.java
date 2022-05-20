package com.lanrenspace.site.service.impl;

import com.lanrenspace.site.base.ResultData;
import com.lanrenspace.site.service.OpenApiService;
import com.lanrenspace.site.service.OptionService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class OpenApiServiceImpl implements OpenApiService {

    private final OptionService optionService;

    OpenApiServiceImpl(OptionService optionService) {
        this.optionService = optionService;
    }

    /**
     * 获取所有的类别
     *
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> getOptions(ServerRequest request) {
        return ResultData.success(this.optionService.getOptions(request).collectList().map(ResultData::ok));
    }

    /**
     * 创建
     *
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> createOption(ServerRequest request) {
        return ResultData.success(this.optionService.createOption(request).map(ResultData::ok));
    }
}
