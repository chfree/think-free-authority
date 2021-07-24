package com.cditer.free.quartz.apimodel.quartztasklog;

import com.cditer.free.quartz.logical.viewmodel.QuartzTaskLogSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:40:57
 * @comment     定时任务日志表
 */

@Data
public class QuartzTaskLogListReq extends BasePagerReq {
    private QuartzTaskLogSearch search;
}