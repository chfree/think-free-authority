package com.cditer.free.devops.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:43:17
 * @comment     项目环境配置
 */

@Data
public class ProjectProfileSettingSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

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
     * 环境模糊搜索
     */
    private String likeProfile;

    /**
     * 分支模糊搜索
     */
    private String likeLabel;

}
