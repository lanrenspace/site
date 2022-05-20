package com.lanrenspace.site.constant;

/**
 * @Author lanrenspace@163.com
 * @Description: 文章状态定义
 **/
public enum ArticleStatusEnum {

    PUBLISHED(1, "已发布"),
    UNPUBLISHED(2, "未发布"),
    TIMED_PUBLISHED(3, "等待定时发布中"),
    DRAFT(0, "草稿");

    /**
     * 状态值
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String description;

    ArticleStatusEnum(Integer code, String description) {
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
