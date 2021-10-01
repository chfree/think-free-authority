package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.logical.dao.IProjectPuburlDao;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 11:00:33
 * @comment     项目公共访问地址
 */

@Component
public class ProjectPuburlDaoImpl extends SuperDao<ProjectPuburl> implements IProjectPuburlDao{
    @Override
    public int queryCountBySearch(ProjectPuburlSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ProjectPuburl.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ProjectPuburl> queryListBySearch(ProjectPuburlSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectPuburl.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, ProjectPuburlSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("project_id",search.getProjectId());

        sqlExpression.andEqNoEmpty("url",search.getUrl());

        sqlExpression.andEqNoEmpty("descr",search.getDescr());

        sqlExpression.andEqNoEmpty("url_mark",search.getUrlMark());

    }
}