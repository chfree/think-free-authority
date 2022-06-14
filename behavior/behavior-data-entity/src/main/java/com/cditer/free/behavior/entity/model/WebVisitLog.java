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
 * @createtime  2022-06-14 12:10:06
 * @comment     操作日志表
 */

@Data
@Entity
@Table(name="bhv_web_visit_log")
public class WebVisitLog extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 全局跟踪号
     */
    @Column(name="trace_id")
    private String traceId;

    /**
     * 开始时间
     */
    @Column(name="start_dt")
    private Date startDt;

    /**
     * 结束时间
     */
    @Column(name="end_dt")
    private Date endDt;

    /**
     * 地址
     */
    @Column(name="url")
    private String url;

    /**
     * 时长
     */
    @Column(name="duration")
    private long duration;

    /**
     * ip地址
     */
    @Column(name="ip")
    private String ip;

    /**
     * 日志类型
     */
    @Column(name="type")
    private String type;

    /**
     * 业务id
     */
    @Column(name="bsn_id")
    private String bsnId;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private String userId;

    /**
     * 角色id
     */
    @Column(name="role_id")
    private String roleId;

    /**
     * 标志1
     */
    @Column(name="mark1")
    private String mark1;

    /**
     * 标志2
     */
    @Column(name="mark2")
    private String mark2;

    /**
     * 标志3
     */
    @Column(name="mark3")
    private String mark3;

    /**
     * 标志4
     */
    @Column(name="mark4")
    private String mark4;

}
