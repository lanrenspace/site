package com.lanrenspace.site.base;

import com.lanrenspace.site.exceptions.ExceptionDef;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

/**
 * @Author lanrenspace@163.com
 * @Description: 响应数据
 **/
@Data
public class ResultData implements Serializable {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 响应消息
     */
    private String msg;


    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static Mono<ServerResponse> success(Object data) {
        return success(Mono.just(ResultData.ok(data)));
    }

    /**
     * 成功响应
     *
     * @param resultDataMono
     * @return
     */
    public static Mono<ServerResponse> success(Mono<ResultData> resultDataMono) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(resultDataMono, ResultData.class);
    }


    /**
     * 失败
     *
     * @param resultData
     * @return
     */
    public static Mono<ServerResponse> fail(ResultData resultData) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(resultData), ResultData.class);
    }

    /**
     * 失败
     *
     * @param errorMsg
     * @return
     */
    public static Mono<ServerResponse> fail(String errorMsg) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ResultData.error(errorMsg)), ResultData.class);
    }

    /**
     * 失败
     *
     * @param def
     * @return
     */
    public static Mono<ServerResponse> fail(ExceptionDef def) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ResultData.error(def.getCode(), def.getMsg())), ResultData.class);
    }

    /**
     * 失败
     *
     * @return
     */
    public static Mono<ServerResponse> fail() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(ResultData.error()), ResultData.class);
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultData ok() {
        return ok(null);
    }

    public static ResultData ok(Object data) {
        ResultData response = new ResultData();
        response.setCode(HttpStatus.OK.value());
        response.setMsg("request success!");
        response.setData(data);
        return response;
    }

    public static ResultData error() {
        ResultData response = new ResultData();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMsg("处理失败");
        return response;
    }

    public static ResultData error(String msg) {
        ResultData response = new ResultData();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMsg(msg);
        return response;
    }

    public static ResultData error(Integer code, String msg) {
        ResultData response = new ResultData();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static ResultData error(String msg, Object data) {
        ResultData response = new ResultData();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMsg(msg);
        response.setData(data);
        return response;
    }


}
