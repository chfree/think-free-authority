package com.cditer.free.devops.logical.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.devops.logical.dao.IProjectPuburlDao;
import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;
import com.cditer.free.devops.logical.service.IProjectPuburlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 11:02:09
 * @comment     项目公共访问地址
 */

@Component
public class ProjectPuburlServiceImpl extends SuperService<ProjectPuburl> implements IProjectPuburlService{
    @Autowired
    IProjectPuburlDao projectPuburlDao;

    @Override
    public int queryCountBySearch(ProjectPuburlSearch search) {
        return projectPuburlDao.queryCountBySearch(search);
    }

    @Override
    public List<ProjectPuburl> queryListBySearch(ProjectPuburlSearch search, PagerModel pagerModel) {
        return projectPuburlDao.queryListBySearch(search,pagerModel);
    }

}
