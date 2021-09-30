package com.cditer.free.devops.dao;

import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:43:46
 * @comment     项目环境配置
 */

public interface IProjectProfileSettingDao extends ISuperDao<ProjectProfileSetting>{
    int queryCountBySearch(ProjectProfileSettingSearch search);

    List<ProjectProfileSetting> queryListBySearch(ProjectProfileSettingSearch search, PagerModel pagerModel);
}