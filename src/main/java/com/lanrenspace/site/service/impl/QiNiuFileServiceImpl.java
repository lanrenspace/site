package com.lanrenspace.site.service.impl;

import com.google.gson.Gson;
import com.lanrenspace.site.service.QiNiuFileService;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class QiNiuFileServiceImpl implements QiNiuFileService {

    @Value("${qiNiu.oss.ak}")
    private String qiNiuAk;

    @Value("${qiNiu.oss.sk}")
    private String qiNiuSk;

    @Value("${qiNiu.oss.bucket}")
    private String qiNiuBucket;

    /**
     * 上传
     *
     * @param filePart
     * @param fileKey
     * @return
     */
    @Override
    public Mono<String> upload(FilePart filePart, String fileKey) {
        String fileContentType = filePart.headers().getFirst("Content-Type");
        try {
            Path tempFile = Files.createTempFile("upload_file", filePart.filename());
            File dest = tempFile.toFile();
            filePart.transferTo(dest).subscribe();
            Configuration configuration = new Configuration(Region.region2());
            UploadManager uploadManager = new UploadManager(configuration);
            Auth auth = Auth.create(qiNiuAk, qiNiuSk);
            String token = auth.uploadToken(qiNiuBucket);

            StringMap params = new StringMap();
            params.put("x:fileName", filePart.filename());

            Response response = uploadManager.put(new FileInputStream(dest), fileKey + ":" + filePart.filename(), token, params, fileContentType);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return Mono.just(putRet.key);
        } catch (IOException e) {
            e.printStackTrace();
            return Mono.empty();
        }
    }
}
