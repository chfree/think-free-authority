package com.cditer.free.devops.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:58:38
 * @comment     项目公共访问地址
 */

@Data
public class ProjectPuburlSearch{
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
     * 访问地址
     */
    private String url;

    /**
     * 访问说明
     */
    private String descr;

    /**
     * 访问标记
     */
    private String urlMark;

}