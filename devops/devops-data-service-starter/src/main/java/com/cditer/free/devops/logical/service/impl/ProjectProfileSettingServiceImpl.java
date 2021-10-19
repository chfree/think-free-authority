package com.cditer.free.devops.logical.service.impl;

import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingSearch;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingView;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.devops.logical.service.IProjectProfileSettingService;
import com.cditer.free.devops.logical.dao.IProjectProfileSettingDao;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:54:20
 * @comment     项目环境配置
 */

@Component
public class ProjectProfileSettingServiceImpl extends SuperService<ProjectProfileSetting> implements IProjectProfileSettingService{
    @Autowired
    IProjectProfileSettingDao projectProfileSettingDao;

    @Override
    public int queryCountBySearch(ProjectProfileSettingSearch search) {
        return projectProfileSettingDao.queryCountBySearch(search);
    }

    @Override
    public List<ProjectProfileSetting> queryListBySearch(ProjectProfileSettingSearch search, PagerModel pagerModel) {
        return projectProfileSettingDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<ProjectProfileSettingView> queryListViewBySearch(ProjectProfileSettingSearch search, PagerModel pagerModel) {
        return projectProfileSettingDao.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public ProjectProfileSettingView queryModelView(String id) {
        return projectProfileSettingDao.queryModelView(id);
    }

}