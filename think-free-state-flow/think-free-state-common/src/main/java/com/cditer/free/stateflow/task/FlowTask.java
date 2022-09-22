package com.cditer.free.stateflow.task;

import lombok.Data;

import java.util.Date;

/**
 * @author C.H
 * @email chfree365@qq.com
 * @createtime 2022/9/22 13:06
 * @comment
 */

@Data
public class FlowTask {
    /**
     * 任务id
     */
    private String taskId;

    /**
     * 来自哪条任务
     */
    private String fromTaskId;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 业务类型
     */
    private String bsnType;

    /**
     * 业务编码
     */
    private String bsnEcd;

    /**
     * 业务状态
     */
    private String bsnStatus;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;


}
