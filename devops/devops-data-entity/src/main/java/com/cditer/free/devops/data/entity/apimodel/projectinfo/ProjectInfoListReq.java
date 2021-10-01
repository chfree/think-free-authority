package com.cditer.free.devops.data.entity.apimodel.projectinfo;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:34:50
 * @comment     项目信息表
 */

@Data
public class ProjectInfoListReq extends BasePagerReq {
    private ProjectInfoSearch search;
}