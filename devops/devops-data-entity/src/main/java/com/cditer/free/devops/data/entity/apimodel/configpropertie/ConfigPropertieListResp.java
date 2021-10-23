package com.cditer.free.devops.data.entity.apimodel.configpropertie;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieView;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-02 13:57:13
 * @comment     属性配置表
 */

@Data
public class ConfigPropertieListResp extends BasePagerResp {
    private List<ConfigPropertieView> configProperties;
}