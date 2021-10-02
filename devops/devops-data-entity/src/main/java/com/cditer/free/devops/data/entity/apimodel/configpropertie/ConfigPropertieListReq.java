package com.cditer.free.devops.data.entity.apimodel.configpropertie;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-02 13:55:42
 * @comment     属性配置表
 */

@Data
public class ConfigPropertieListReq extends BasePagerReq {
    private ConfigPropertieSearch search;
}