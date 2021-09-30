package com.cditer.free.devops.service;

import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:31:28
 * @comment     项目信息表
 */

public interface IProjectInfoService extends ISuperService<ProjectInfo>{
    int queryCountBySearch(ProjectInfoSearch search);

    List<ProjectInfo> queryListBySearch(ProjectInfoSearch search, PagerModel pagerModel);
}