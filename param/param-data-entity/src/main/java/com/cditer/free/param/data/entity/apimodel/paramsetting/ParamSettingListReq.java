package com.cditer.free.param.data.entity.apimodel.paramsetting;

import com.cditer.free.param.data.entity.viewmodel.ParamSettingSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 12:51:57
 * @comment     参数配置表
 */

@Data
public class ParamSettingListReq extends BasePagerReq {
    private ParamSettingSearch search;
}