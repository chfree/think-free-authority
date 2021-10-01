package com.cditer.free.devops.data.entity.apimodel.projectinfo;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:36:35
 * @comment     项目信息表
 */

@Data
public class ProjectInfoListResp extends BasePagerResp {
    private List<ProjectInfo> projectInfos;
}