package com.cditer.free.behavior.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-15 12:50:10
 * @comment     web访问控制表
 */

@Data
@Entity
@Table(name="bhv_web_visit_limit")
public class WebVisitLimit extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 访问类型
     */
    @Column(name="visit_type")
    private String visitType;

    /**
     * 控制类型
     */
    @Column(name="limit_type")
    private String limitType;

    /**
     * 最大数量
     */
    @Column(name="max_count")
    private Integer maxCount;

    /**
     * 状态
     */
    @Column(name="status")
    private String status;

    /**
     * 启用日期
     */
    @Column(name="enable_dt")
    private Date enableDt;

}