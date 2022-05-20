package com.lanrenspace.site.entity;

import com.lanrenspace.site.base.DataEntity;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @Author lanrenspace@163.com
 * @Description: 文章
 **/
@Data
@Table("site_article")
public class Article extends DataEntity<Long> {

    /**
     * 发表用户ID
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 摘要
     */
    private String contentShort;

    /**
     * 浏览量
     */
    private Integer views;

    /**
     * 评论总数
     */
    private Integer commentCount;

    /**
     * 点赞总数
     */
    private Integer likeCount;

    /**
     * 字数
     */
    private Integer wordCount;

    /**
     * 发表日期
     */
    private LocalDateTime issueDatetime;

    /**
     * 状态
     * com.lanrenspace.site.constant.ArticleStatusEnum
     */
    private Integer status;

    /**
     * 定时发布时间 if status == timed_published
     */
    private LocalDateTime timed;

    /**
     * 定时发布表达式
     */
    private String timedCron;

    /**
     * 展现形式
     * com.lanrenspace.site.constant.ArticlePreFromEnum
     */
    private Integer preFrom;

    /**
     * 大栏目对应值
     */
    private Long optionId;
}
