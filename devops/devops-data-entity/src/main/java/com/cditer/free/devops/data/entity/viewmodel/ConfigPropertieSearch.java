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
     * 项目环境id
     */
    private String projectProfileId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 环境
     */
    private String profile;

    /**
     * 分支
     */
    private String label;

    /**
     * 键
     */
    private String settingKey;

    /**
     * 值
     */
    private String settingValue;

    /**
     * 状态
     */
    private String status;

    /**
     * 环境
     */
    private String likeProfile;

    /**
     * 分支
     */
    private String likeLabel;

    /**
     * 键
     */
    private String likeSettingKey;

    /**
     * 值
     */
    private String likeSettingValue;

}