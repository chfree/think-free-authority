package com.tennetcn.free.quartz.apimodel.quartztasklog;

import com.tennetcn.free.quartz.logical.model.QuartzTaskLog;
import com.tennetcn.free.core.message.web.BasePagerResp;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:42:27
 * @comment     定时任务日志表
 */

@Data
public class QuartzTaskLogListResp extends BasePagerResp {
    private List<QuartzTaskLog> quartzTaskLogs;
}