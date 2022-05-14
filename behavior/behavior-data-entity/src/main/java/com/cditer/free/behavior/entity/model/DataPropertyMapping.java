package com.cditer.free.behavior.entity.model;


import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-14 10:23:45
 * @comment     属性映射表
 */

@Data
@Entity
@Table(name="bhv_data_property_mapping")
public class DataPropertyMapping extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 业务类型
     */
    @Column(name="bsn_type")
    private String bsnType;

    /**
     * 名称
     */
    @Column(name="name")
    private String name;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 类别
     */
    @Column(name="cagetory")
    private String cagetory;

    /**
     * 转换
     */
    @Column(name="convert")
    private String convert;

}
