package com.lanrenspace.site.service.impl;

import com.lanrenspace.site.base.ResultData;
import com.lanrenspace.site.exceptions.ExceptionDef;
import com.lanrenspace.site.exceptions.ServiceException;
import com.lanrenspace.site.service.OpenApiService;
import com.lanrenspace.site.service.OptionService;
import com.lanrenspace.site.service.QiNiuFileService;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class OpenApiServiceImpl implements OpenApiService {

    private final OptionService optionService;
    private final QiNiuFileService fileService;

    OpenApiServiceImpl(OptionService optionService, QiNiuFileService fileService) {
        this.optionService = optionService;
        this.fileService = fileService;
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

    /**
     * 上传文件
     *
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> uploadFile(ServerRequest request) {
        Mono<ResultData> resultData = request.multipartData().publishOn(Schedulers.boundedElastic()).flatMap(parts -> {
            Map<String, Part> partMap = parts.toSingleValueMap();
            if (!partMap.containsKey("file") || !request.queryParam("fileKey").isPresent()) {
                return Mono.error(new ServiceException(ExceptionDef.REQ_PARAMS_IS_NULL));
            }
            FilePart filePart = (FilePart) partMap.get("file");
            String fileKey = request.queryParam("fileKey").orElse("default_none_key");
            return fileService.upload(filePart, fileKey);
        }).map(ResultData::ok);
        return ResultData.success(resultData);
    }
}
