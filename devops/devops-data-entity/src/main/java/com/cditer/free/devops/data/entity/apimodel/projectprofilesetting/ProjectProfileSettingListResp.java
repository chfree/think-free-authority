package com.cditer.free.devops.data.entity.apimodel.projectprofilesetting;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingView;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:52:35
 * @comment     项目环境配置
 */

@Data
public class ProjectProfileSettingListResp extends BasePagerResp {
    private List<ProjectProfileSettingView> projectProfileSettings;
}