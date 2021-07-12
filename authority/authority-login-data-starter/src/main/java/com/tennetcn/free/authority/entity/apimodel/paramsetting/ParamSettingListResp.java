package com.tennetcn.free.authority.entity.apimodel.paramsetting;

import com.tennetcn.free.authority.entity.model.ParamSetting;
import com.tennetcn.free.core.message.web.BasePagerResp;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 12:51:19
 * @comment     参数配置表
 */

@Data
public class ParamSettingListResp extends BasePagerResp {
    private List<ParamSetting> paramSettings;
}