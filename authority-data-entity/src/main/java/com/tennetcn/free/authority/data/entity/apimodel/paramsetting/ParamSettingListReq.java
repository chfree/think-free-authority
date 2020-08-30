package com.tennetcn.free.authority.data.entity.apimodel.paramsetting;

import com.tennetcn.free.authority.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
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