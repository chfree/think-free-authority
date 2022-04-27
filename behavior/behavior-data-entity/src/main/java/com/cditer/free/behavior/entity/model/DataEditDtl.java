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
 * @createtime  2022-04-27 21:08:56
 * @comment     数据修改详情
 */

@Data
@Entity
@Table(name="bhv_data_edit_dtl")
public class DataEditDtl extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 修改记录id
     */
    @Column(name="edit_id")
    private String editId;

    /**
     * 级别
     */
    @Column(name="level")
    private Integer level;

    /**
     * 属性名
     */
    @Column(name="pro_name")
    private String proName;

    /**
     * 属性文本
     */
    @Column(name="pro_text")
    private String proText;

    /**
     * 新值
     */
    @Column(name="new_val")
    private String newVal;

    /**
     * 原始值
     */
    @Column(name="old_val")
    private String oldVal;

}