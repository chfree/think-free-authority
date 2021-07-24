package com.cditer.free.quartz.apimodel.quartztask;

import com.cditer.free.quartz.logical.viewmodel.QuartzTaskSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-03-01 11:31:06
 * @comment     定时任务表
 */

@Data
public class QuartzTaskListReq extends BasePagerReq {
    private QuartzTaskSearch search;
}