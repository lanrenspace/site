package com.lanrenspace.site.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description: 七牛云对象存储
 **/
public interface QiNiuFileService {

    /**
     * 上传
     *
     * @param filePart
     * @param fileKey
     * @return
     */
    Mono<String> upload(FilePart filePart, String fileKey);
}
