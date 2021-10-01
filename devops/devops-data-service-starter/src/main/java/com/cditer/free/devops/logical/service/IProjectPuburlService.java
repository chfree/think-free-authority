package com.cditer.free.devops.logical.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 11:01:26
 * @comment     项目公共访问地址
 */

public interface IProjectPuburlService extends ISuperService<ProjectPuburl>{
    int queryCountBySearch(ProjectPuburlSearch search);

    List<ProjectPuburl> queryListBySearch(ProjectPuburlSearch search, PagerModel pagerModel);
}