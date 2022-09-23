package com.cditer.free.stateflow.data.entity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-09-23 13:10:43
 * @comment     流程待办任务
 */

@Data
@Entity
@Table(name="flow_todo_task")
public class TodoTask extends ModelBase {
    /**
     * 任务id
     */
    @Id
    @Column(name="task_id")
    private String taskId;

    /**
     * 来自哪条任务
     */
    @Column(name="from_task_id")
    private String fromTaskId;

    /**
     * 任务状态
     */
    @Column(name="task_status")
    private String taskStatus;

    /**
     * 业务类型
     */
    @Column(name="bsn_type")
    private String bsnType;

    /**
     * 业务编码
     */
    @Column(name="bsn_ecd")
    private String bsnEcd;

    /**
     * 业务状态
     */
    @Column(name="bsn_status")
    private String bsnStatus;

    /**
     * 开始时间
     */
    @Column(name="start_date")
    private Date startDate;

    /**
     * 结束时间
     */
    @Column(name="end_date")
    private Date endDate;

}
