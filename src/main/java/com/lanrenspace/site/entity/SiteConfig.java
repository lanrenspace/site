package com.lanrenspace.site.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

/**
 * @Author lanrenspace@163.com
 * @Description: 站点配置
 **/
@Table("site_config")
@Data
public class SiteConfig implements Serializable {

    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 配置项 json obj
     */
    private String content;

    /**
     * 版本
     */
    private Integer version;
}
