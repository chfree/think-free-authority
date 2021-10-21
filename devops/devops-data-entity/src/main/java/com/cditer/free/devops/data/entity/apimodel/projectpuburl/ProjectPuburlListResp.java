package com.cditer.free.devops.data.entity.apimodel.projectpuburl;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlView;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:56:39
 * @comment     项目公共访问地址
 */

@Data
public class ProjectPuburlListResp extends BasePagerResp {
    private List<ProjectPuburlView> projectPuburls;
}