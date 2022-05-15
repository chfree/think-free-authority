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
 * @createtime  2022-05-15 21:40:47
 * @comment     数据实体信息
 */

@Data
@Entity
@Table(name="bhv_data_entity_info")
public class DataEntityInfo extends ModelBase {
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
     * 业务类型
     */
    @Column(name="bsn_type")
    private String bsnType;

    /**
     * 表名
     */
    @Column(name="table_name")
    private String tableName;

}
