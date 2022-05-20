package com.lanrenspace.site.exceptions;

import java.util.function.Supplier;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
public class ConfigException extends RuntimeException implements Supplier<ExceptionDef> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    public ConfigException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ConfigException(ExceptionDef exceptionDef) {
        this.code = exceptionDef.getCode();
        this.msg = exceptionDef.getMsg();
    }

    public ConfigException() {

    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public ExceptionDef get() {
        return ExceptionDef.REQ_PARAMS_IS_NULL;
    }
}
