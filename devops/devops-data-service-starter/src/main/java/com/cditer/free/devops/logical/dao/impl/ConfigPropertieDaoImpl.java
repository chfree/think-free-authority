package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.model.ProjectProfileSetting;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieView;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.logical.dao.IConfigPropertieDao;

import java.util.List;


/**
 * @author auto build code by think
 * @email chfree001@gmail.com
 * @createtime 2021-10-01 08:46:37
 * @comment 属性配置表
 */

@Component
public class ConfigPropertieDaoImpl extends SuperDao<ConfigPropertie> implements IConfigPropertieDao {
    @Override
    public int queryCountBySearch(ConfigPropertieSearch search) {
        ISqlExpression sqlExpression =queryViewSql(true);

        appendExpression(sqlExpression, search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ConfigPropertie> queryListBySearch(ConfigPropertieSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ConfigPropertie.class);

        appendExpression(sqlExpression, search);

        return queryList(sqlExpression, pagerModel);
    }

    private ISqlExpression queryViewSql(boolean count) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        if(count){
            sqlExpression.selectCount().from(ConfigPropertie.class, "cp");
        }else {
            sqlExpression.selectAllFrom(ConfigPropertie.class, "cp")
                    .appendSelect("pi", ProjectInfo::getName, "projectName")
                    .appendSelect("pps", ProjectProfileSetting::getProfile)
                    .appendSelect("pps", ProjectProfileSetting::getLabel);
        }
        sqlExpression.leftJoin(ProjectProfileSetting.class, "pps")
                .on(ConfigPropertie::getProjectProfileId, "cp", ProjectProfileSetting::getId, "pps")
                .leftJoin(ProjectInfo.class, "pi")
                .on(ProjectInfo::getId, "pi", ProjectProfileSetting::getProjectId, "pps");

        return sqlExpression;
    }

    @Override
    public List<ConfigPropertieView> queryListViewBySearch(ConfigPropertieSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression =queryViewSql(false);

        appendExpression(sqlExpression, search);

        return queryList(sqlExpression, pagerModel, ConfigPropertieView.class);
    }

    @Override
    public ConfigPropertieView queryModelView(String id) {
        ISqlExpression sqlExpression =queryViewSql(false);
        sqlExpression.andEq(ConfigPropertie::getId, id);

        return queryModel(sqlExpression, ConfigPropertieView.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, ConfigPropertieSearch search) {
        sqlExpression.andEqNoEmpty(ConfigPropertie::getId, search.getId());

        sqlExpression.andNotEqNoEmpty(ConfigPropertie::getId, search.getNotId());

        sqlExpression.andEqNoEmpty(ConfigPropertie::getSettingKey, search.getSettingKey());

        sqlExpression.andEqNoEmpty(ConfigPropertie::getSettingValue, search.getSettingValue());

        sqlExpression.andEqNoEmpty(ConfigPropertie::getProjectProfileId, search.getProjectProfileId());

        sqlExpression.andEqNoEmpty(ConfigPropertie::getStatus, search.getStatus());

        sqlExpression.andEqNoEmpty("pps", ProjectProfileSetting::getProfile, search.getProfile());

        sqlExpression.andEqNoEmpty("pps", ProjectProfileSetting::getLabel, search.getLabel());

        sqlExpression.andLikeNoEmpty("pps", ProjectProfileSetting::getProfile, search.getLikeProfile());

        sqlExpression.andLikeNoEmpty("pps", ProjectProfileSetting::getLabel, search.getLikeLabel());

        sqlExpression.andEqNoEmpty("pps", ProjectProfileSetting::getProjectId, search.getProjectId());

        sqlExpression.andLikeNoEmpty(ConfigPropertie::getSettingKey, search.getLikeSettingKey());

        sqlExpression.andLikeNoEmpty(ConfigPropertie::getSettingValue, search.getLikeSettingValue());
    }
}