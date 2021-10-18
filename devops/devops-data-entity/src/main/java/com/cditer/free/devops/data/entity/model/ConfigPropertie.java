package com.cditer.free.devops.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 08:44:13
 * @comment     属性配置表
 */

@Data
@Entity
@Table(name="dps_config_properties")
public class ConfigPropertie extends ModelBase {

    /**
     * 项目id
     */
    @Column(name="project_id")
    private String projectId;

    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 键
     */
    @Column(name="setting_key")
    private String settingKey;

    /**
     * 值
     */
    @Column(name="setting_value")
    private String settingValue;

}