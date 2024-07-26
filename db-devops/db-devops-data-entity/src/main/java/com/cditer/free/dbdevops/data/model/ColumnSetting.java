package com.cditer.free.dbdevops.data.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-07-25 12:56:09
 * @comment     列信息配置
 */

@Data
@Entity
@Table(name="base_param_column_setting")
public class ColumnSetting extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 表信息主键
     */
    @Column(name="table_setting_id")
    private String tableSettingId;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 属性名
     */
    @Column(name="prop_name")
    private String propName;

    /**
     * 列名
     */
    @Column(name="column_name")
    private String columnName;

    /**
     * 是否主键
     */
    @Column(name="wthr_key")
    private String wthrKey;

    /**
     * 数据格式
     */
    @Column(name="data_format")
    private String dataFormat;

    /**
     * 是否查询
     */
    @Column(name="wthr_query")
    private String wthrQuery;

    /**
     * 显示顺序
     */
    @Column(name="display_seq")
    private Integer displaySeq;

    /**
     * 查询方式
     */
    @Column(name="query_type")
    private String queryType;

    /**
     * 排序方式
     */
    @Column(name="order_type")
    private String orderType;

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