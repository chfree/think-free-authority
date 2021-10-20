package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingSearch;
import com.cditer.free.devops.data.entity.viewmodel.ProjectProfileSettingView;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.logical.dao.IProjectProfileSettingDao;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:52:16
 * @comment     项目环境配置
 */

@Component
public class ProjectProfileSettingDaoImpl extends SuperDao<ProjectProfileSetting> implements IProjectProfileSettingDao{
    @Override
    public int queryCountBySearch(ProjectProfileSettingSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ProjectProfileSetting.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ProjectProfileSetting> queryListBySearch(ProjectProfileSettingSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectProfileSetting.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<ProjectProfileSettingView> queryListViewBySearch(ProjectProfileSettingSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectProfileSetting.class, "pps")
                .appendSelect("pi.name as projectName")
                .leftJoin(ProjectInfo.class, "pi").on("pi.id", "pps.project_id");

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel, ProjectProfileSettingView.class);
    }

    @Override
    public ProjectProfileSettingView queryModelView(String id) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectProfileSetting.class, "pps")
                .appendSelect("pi", ProjectInfo::getName, "projectName")
                .leftJoin(ProjectInfo.class, "pi")
                .on(ProjectInfo::getId, "pi", ProjectProfileSetting::getProjectId,"pps")
                .andEq("pps.id", id);

        return queryModel(sqlExpression, ProjectProfileSettingView.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, ProjectProfileSettingSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("project_id",search.getProjectId());

        sqlExpression.andEqNoEmpty("profile",search.getProfile());

        sqlExpression.andEqNoEmpty("label",search.getLabel());

        sqlExpression.andLikeNoEmpty(ProjectProfileSettingSearch::getProfile, search.getLikeProfile());

        sqlExpression.andLikeNoEmpty(ProjectProfileSettingSearch::getLabel,search.getLikeLabel());
    }
}