package com.cditer.free.devops.data.entity.apimodel.projectprofilesetting;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingSearch;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:51:34
 * @comment     项目环境配置
 */

@Data
public class ProjectProfileSettingListReq extends BasePagerReq {
    private ProjectProfileSettingSearch search;
}