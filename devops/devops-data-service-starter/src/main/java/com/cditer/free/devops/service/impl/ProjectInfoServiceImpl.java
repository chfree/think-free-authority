package com.cditer.free.devops.service.impl;

import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.devops.service.IProjectInfoService;
import com.cditer.free.devops.dao.IProjectInfoDao;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:37:23
 * @comment     项目信息表
 */

@Component
public class ProjectInfoServiceImpl extends SuperService<ProjectInfo> implements IProjectInfoService{
    @Autowired
    IProjectInfoDao projectInfoDao;

    @Override
    public int queryCountBySearch(ProjectInfoSearch search) {
        return projectInfoDao.queryCountBySearch(search);
    }

    @Override
    public List<ProjectInfo> queryListBySearch(ProjectInfoSearch search, PagerModel pagerModel) {
        return projectInfoDao.queryListBySearch(search,pagerModel);
    }

}