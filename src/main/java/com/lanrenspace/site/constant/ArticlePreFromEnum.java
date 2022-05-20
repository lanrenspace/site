package com.lanrenspace.site.constant;

/**
 * @Author lanrenspace@163.com
 * @Description: 文章展现形式定义
 **/
public enum ArticlePreFromEnum {

    PUBLIC_PRE(1, "公开"),
    PRIVATE_PRE(2, "私密");

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String description;

    ArticlePreFromEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
