package com.lanrenspace.site.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Data
public class OptionInfoVO implements Serializable {

    /**
     * 类别ID
     */
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 类别值
     */
    private String value;

    /**
     * 类别描述
     */
    private String description;
}
