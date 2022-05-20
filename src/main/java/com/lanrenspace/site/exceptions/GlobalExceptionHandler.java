package com.lanrenspace.site.exceptions;

import com.alibaba.fastjson2.JSON;
import com.lanrenspace.site.base.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import javax.crypto.BadPaddingException;

/**
 * @Author lanrenspace@163.com
 * @Description: 全局异常处理
 **/
@Slf4j
@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler, ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return this.errorConvert(exchange, ex);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return this.errorConvert(exchange, denied);
    }


    /**
     * 异常转换
     *
     * @param webExchange
     * @param ex
     * @return
     */
    private Mono<Void> errorConvert(ServerWebExchange webExchange, Throwable ex) {
        ServerHttpResponse response = webExchange.getResponse();
        String path = webExchange.getRequest().getPath().toString();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        ResultData resultData = ResultData.error(ex.getMessage());
        if (ex instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) ex;
            StackTraceElement stackTraceElement = serviceException.getStackTrace()[0];
            log.error("path:{}  req error:", path);
            log.error("className:{}", stackTraceElement.getClassName());
            log.error("methodName:{}", stackTraceElement.getMethodName());
            log.error("lineNumber:{}", stackTraceElement.getLineNumber());
            resultData = ResultData.error(serviceException.getCode(), serviceException.getMsg());
        } else if (ex instanceof BadPaddingException) {
            BadPaddingException badPaddingException = (BadPaddingException) ex;
            StackTraceElement stackTraceElement = badPaddingException.getStackTrace()[0];
            log.error("path:{}  req error:", path);
            log.error("className:{}", stackTraceElement.getClassName());
            log.error("methodName:{}", stackTraceElement.getMethodName());
            log.error("lineNumber:{}", stackTraceElement.getLineNumber());
            resultData = ResultData.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "禁止非法请求!");
        } else {
            ex.printStackTrace();
        }
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(resultData));
        return response.writeWith(Mono.just(dataBuffer));
    }

}
