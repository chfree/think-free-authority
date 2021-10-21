package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.model.ProjectPuburl;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlView;
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

    @Override
    public List<ProjectPuburlView> queryListViewBySearch(ProjectPuburlSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = queryViewSql();

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel, ProjectPuburlView.class);
    }

    @Override
    public ProjectPuburlView queryModelView(String id) {
        ISqlExpression sqlExpression = queryViewSql();

        sqlExpression.andEq(ProjectPuburl::getId, id);

        return queryModel(sqlExpression, ProjectPuburlView.class);
    }

    private ISqlExpression queryViewSql(){
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectPuburl.class, "pp")
                .appendSelect("pi", ProjectInfo::getName, "projectName")
                .leftJoin(ProjectInfo.class, "pi")
                .on(ProjectInfo::getId, "pi", ProjectPuburl::getProjectId,"pp");

        return sqlExpression;
    }

    private void appendExpression(ISqlExpression sqlExpression, ProjectPuburlSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("project_id",search.getProjectId());

        sqlExpression.andEqNoEmpty("url",search.getUrl());

        sqlExpression.andEqNoEmpty("descr",search.getDescr());

        sqlExpression.andEqNoEmpty("url_mark",search.getUrlMark());

        sqlExpression.andLikeNoEmpty(ProjectPuburl::getUrl, search.getLikeUrl());

        sqlExpression.andLikeNoEmpty(ProjectPuburl::getUrlMark, search.getLikeUrlMark());

    }
}