package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.logical.dao.IProjectInfoDao;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:29:59
 * @comment     项目信息表
 */

@Component
public class ProjectInfoDaoImpl extends SuperDao<ProjectInfo> implements IProjectInfoDao{
    @Override
    public int queryCountBySearch(ProjectInfoSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ProjectInfo.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ProjectInfo> queryListBySearch(ProjectInfoSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ProjectInfo.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, ProjectInfoSearch search){
        sqlExpression.andEqNoEmpty(ProjectInfo::getId,search.getId());

        sqlExpression.andNotEqNoEmpty(ProjectInfo::getId,search.getNotId());

        sqlExpression.andEqNoEmpty(ProjectInfo::getProjectNo,search.getProjectNo());

        sqlExpression.andEqNoEmpty(ProjectInfo::getProjectCodeName,search.getProjectCodeName());

        sqlExpression.andEqNoEmpty(ProjectInfo::getName,search.getName());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getName, search.getLikeName());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getProjectNo, search.getLikeProjectNo());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getProjectCodeName, search.getLikeProjectCodeName());

        sqlExpression.andEqNoEmpty(ProjectInfo::getCatalogName,search.getCatalogName());

        sqlExpression.andEqNoEmpty(ProjectInfo::getServerName,search.getServerName());

        sqlExpression.andEqNoEmpty(ProjectInfo::getBelongServiceRegistry,search.getBelongServiceRegistry());

        sqlExpression.andEqNoEmpty(ProjectInfo::getProjectType,search.getProjectType());

        sqlExpression.andEqNoEmpty(ProjectInfo::getGitAddress,search.getGitAddress());

        sqlExpression.andEqNoEmpty(ProjectInfo::getContextPath,search.getContextPath());

        sqlExpression.andEqNoEmpty(ProjectInfo::getLanguage,search.getLanguage());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getLanguage,search.getLikeLanguage());

        sqlExpression.andEqNoEmpty(ProjectInfo::getPort, search.getPort());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getPort, search.getLikePort());

        sqlExpression.andEqNoEmpty(ProjectInfo::getParentId, search.getParentId());

        sqlExpression.andLikeNoEmpty(ProjectInfo::getParentId, search.getLikeParentName());
    }
}