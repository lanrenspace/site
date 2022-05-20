package com.lanrenspace.site.base;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author lanrenspace@163.com
 * @Description: 业务实体
 **/
@Data
public class DataEntity<ID> implements Serializable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 是否删除 1：已删除 0：未删除
     */
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建用户编码
     */
    private String createCode;

    /**
     * 创建名称
     */
    private String createName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 根据用户编码
     */
    private String updateCode;

    /**
     * 根据用户名称
     */
    private String updateName;
}
