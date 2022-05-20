package com.lanrenspace.site.entity;

import com.lanrenspace.site.base.DataEntity;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @Author lanrenspace@163.com
 * @Description: 类别信息
 **/
@Data
@Table("site_option")
public class Option extends DataEntity<Long> {

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

}
