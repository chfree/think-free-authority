package com.cditer.free.devops.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:42:07
 * @comment     项目环境配置
 */

@Data
@Entity
@Table(name="dps_project_profile_setting")
public class ProjectProfileSetting extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 项目id
     */
    @Column(name="project_id")
    private String projectId;

    /**
     * 环境
     */
    @Column(name="profile")
    private String profile;

    /**
     * 分支
     */
    @Column(name="label")
    private String label;

    /**
     * 备注
     */
    @Column(name="remark")
    private String remark;

}