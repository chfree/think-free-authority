package com.tennetcn.free.authority.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:26:08
 * @comment     参数配置表
 */

@Data
@Entity
@Table(name="base_param_setting")
public class ParamSetting extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 参数名称
     */
    @Column(name="name")
    private String name;

    /**
     * 参数标题
     */
    @Column(name="title")
    private String title;

    /**
     * 参数值
     */
    @Column(name="param_value")
    private String paramValue;

    /**
     * 备注
     */
    @Column(name="comments")
    private String comments;

}