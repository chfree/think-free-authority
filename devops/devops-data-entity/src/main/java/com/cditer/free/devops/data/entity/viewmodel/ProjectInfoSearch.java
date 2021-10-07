package com.cditer.free.devops.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:24:41
 * @comment     项目信息表
 */

@Data
public class ProjectInfoSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 项目编号
     */
    private String projectNo;

    /**
     * 项目代号
     */
    private String projectCodeName;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目名称模糊搜索
     */
    private String likeName;

    /**
     * 目录名称
     */
    private String catalogName;

    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 所属注册中心
     */
    private String belongServiceRegistry;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * git地址
     */
    private String gitAddress;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 虚拟路径
     */
    private String contextPath;

    /**
     * 项目语言
     */
    private String language;

    /**
     * 项目说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}