package com.cditer.free.devops.logical.dao;


import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:25:10
 * @comment     项目信息表
 */

public interface IProjectInfoDao extends ISuperDao<ProjectInfo>{
    int queryCountBySearch(ProjectInfoSearch search);

    List<ProjectInfo> queryListBySearch(ProjectInfoSearch search, PagerModel pagerModel);
}