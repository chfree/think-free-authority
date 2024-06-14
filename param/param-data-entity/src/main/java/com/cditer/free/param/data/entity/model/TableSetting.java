package com.cditer.free.param.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:26:19
 * @comment     表信息配置
 */

@Data
@Entity
@Table(name="base_param_table_setting")
public class TableSetting extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 表名
     */
    @Column(name="table_name")
    private String tableName;

    /**
     * 备注
     */
    @Column(name="comment")
    private String comment;

    /**
     * 备注1
     */
    @Column(name="rmrk1")
    private String rmrk1;

    /**
     * 备注2
     */
    @Column(name="rmrk2")
    private String rmrk2;

}
