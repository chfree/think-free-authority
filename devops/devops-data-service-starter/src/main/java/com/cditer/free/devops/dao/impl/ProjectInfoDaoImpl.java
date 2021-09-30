package com.cditer.free.devops.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.dao.IProjectInfoDao;
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
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("project_no",search.getProjectNo());

        sqlExpression.andEqNoEmpty("project_code_name",search.getProjectCodeName());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("catalog_name",search.getCatalogName());

        sqlExpression.andEqNoEmpty("server_name",search.getServerName());

        sqlExpression.andEqNoEmpty("belong_service_registry",search.getBelongServiceRegistry());

        sqlExpression.andEqNoEmpty("project_type",search.getProjectType());

        sqlExpression.andEqNoEmpty("git_address",search.getGitAddress());

        sqlExpression.andEqNoEmpty("port",search.getPort());

        sqlExpression.andEqNoEmpty("context_path",search.getContextPath());

        sqlExpression.andEqNoEmpty("language",search.getLanguage());

        sqlExpression.andEqNoEmpty("description",search.getDescription());

        sqlExpression.andEqNoEmpty("remark",search.getRemark());

    }
}