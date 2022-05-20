package com.lanrenspace.site.exceptions;

/**
 * @Author lanrenspace@163.com
 * @Description: 异常定义
 **/
public enum ExceptionDef {

    INTERFACE_OVERRIDE(55001, "禁止接口越权!"),
    REQ_PARAMS_IS_NULL(54001, "请求参数不能为空!"),
    USER_NOT_EXIST(55002, "用户不存在!"),
    REQ_TOKEN_FORMAT_ERROR(55003, "请求令牌格式错误!"),
    REQ_TOKEN_IS_NULL(55004, "请求令牌不能为空!"),
    CREATE_TOKEN_EXCEPTION(55005, "令牌创建异常!"),
    USER_EMAIL_EXIST(55006, "登录邮箱已存在,无法重复注册!");

    ExceptionDef(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
