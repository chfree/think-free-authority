package com.tennetcn.free.quartz.apimodel.quartztask;

import com.tennetcn.free.core.message.web.BasePagerResp;
import com.tennetcn.free.quartz.logical.model.QuartzTask;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:31:47
 * @comment     定时任务表
 */

@Data
public class QuartzTaskListResp extends BasePagerResp {
    private List<QuartzTask> quartzTasks;
}
