package com.cditer.free.devops.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:22:17
 * @comment     项目信息表
 */

@Data
@Entity
@Table(name="dps_project_info")
public class ProjectInfo extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 项目编号
     */
    @Column(name="project_no")
    private String projectNo;

    /**
     * 项目代号
     */
    @Column(name="project_code_name")
    private String projectCodeName;

    /**
     * 项目名称
     */
    @Column(name="name")
    private String name;

    /**
     * 目录名称
     */
    @Column(name="catalog_name")
    private String catalogName;

    /**
     * 服务名称
     */
    @Column(name="server_name")
    private String serverName;

    /**
     * 所属注册中心
     */
    @Column(name="belong_service_registry")
    private String belongServiceRegistry;

    /**
     * 项目类型
     */
    @Column(name="project_type")
    private String projectType;

    /**
     * git地址
     */
    @Column(name="git_address")
    private String gitAddress;

    /**
     * 父级项目
     */
    @Column(name="parent_id")
    private String parent_id;

    /**
     * 创建时间
     */
    @Column(name="create_date")
    private Date createDate;

    /**
     * 端口号
     */
    @Column(name="port")
    private Integer port;

    /**
     * 虚拟路径
     */
    @Column(name="context_path")
    private String contextPath;

    /**
     * 项目语言
     */
    @Column(name="language")
    private String language;

    /**
     * 项目说明
     */
    @Column(name="description")
    private String description;

    /**
     * 备注
     */
    @Column(name="remark")
    private String remark;

}