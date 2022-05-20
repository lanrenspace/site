package com.lanrenspace.site.base;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public abstract class ServerRouters {

    /**
     * 服务跟路径
     */
    private static String ROOT_PATTERN = "";

    public ServerRouters(String rootPattern) {
        ROOT_PATTERN = rootPattern;
    }

    /**
     * 构建get路由
     *
     * @param path
     * @param handlerFunction
     * @param <T>
     * @return
     */
    protected <T extends ServerResponse> RouterFunction<T> buildGetRoute(String path, HandlerFunction<T> handlerFunction) {
        return this.buildGetRoute(path, handlerFunction, MediaType.APPLICATION_JSON);
    }


    /**
     * 构建get路由
     *
     * @param path
     * @param handlerFunction
     * @param mediaType
     * @param <T>
     * @return
     */
    protected <T extends ServerResponse> RouterFunction<T> buildGetRoute(String path, HandlerFunction<T> handlerFunction, MediaType mediaType) {
        if (null == mediaType) {
            mediaType = MediaType.APPLICATION_JSON;
        }
        return RouterFunctions.route(this.buildGetRequestPredicate(path, mediaType), handlerFunction);
    }


    /**
     * 构建post路由
     *
     * @param path
     * @param handlerFunction
     * @param <T>
     * @return
     */
    protected <T extends ServerResponse> RouterFunction<T> buildPostRoute(String path, HandlerFunction<T> handlerFunction) {
        return this.buildPostRoute(path, handlerFunction, MediaType.APPLICATION_JSON);
    }


    /**
     * 构建post路由
     *
     * @param path
     * @param handlerFunction
     * @param mediaType
     * @param <T>
     * @return
     */
    protected <T extends ServerResponse> RouterFunction<T> buildPostRoute(String path, HandlerFunction<T> handlerFunction, MediaType mediaType) {
        if (null == mediaType) {
            mediaType = MediaType.APPLICATION_JSON;
        }
        return RouterFunctions.route(this.buildPostRequestPredicate(path, mediaType), handlerFunction);
    }


    /**
     * get
     *
     * @param path
     * @param mediaType
     * @return
     */
    protected RequestPredicate buildGetRequestPredicate(String path, MediaType mediaType) {
        return RequestPredicates.GET(ROOT_PATTERN + path).and(RequestPredicates.accept(mediaType));
    }

    /**
     * get
     *
     * @param path
     * @return
     */
    protected RequestPredicate buildGetRequestPredicate(String path) {
        return this.buildGetRequestPredicate(path,MediaType.APPLICATION_JSON);
    }

    /**
     * post
     *
     * @param path
     * @param mediaType
     * @return
     */
    protected RequestPredicate buildPostRequestPredicate(String path, MediaType mediaType) {
        return RequestPredicates.POST(ROOT_PATTERN + path).and(RequestPredicates.accept(mediaType));
    }

    /**
     * 获取完整请求路径
     * @param path
     * @return
     */
    protected String getPath(String path) {
        return ROOT_PATTERN + (path.startsWith("/") ? path : "/" + path);
    }
}
