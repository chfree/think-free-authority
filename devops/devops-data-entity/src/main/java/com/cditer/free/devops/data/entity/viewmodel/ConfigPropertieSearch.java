package com.cditer.free.devops.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 08:45:25
 * @comment     属性配置表
 */

@Data
public class ConfigPropertieSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 应用名称
     */
    private String application;

    /**
     * 环境
     */
    private String profile;

    /**
     * 分支
     */
    private String label;

}