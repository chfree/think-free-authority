package com.cditer.free.devops.logical.dao.impl;

import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.data.entity.model.ConfigPropertie;
import com.cditer.free.devops.data.entity.viewmodel.ConfigPropertieSearch;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.devops.logical.dao.IConfigPropertieDao;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 08:46:37
 * @comment     属性配置表
 */

@Component
public class ConfigPropertieDaoImpl extends SuperDao<ConfigPropertie> implements IConfigPropertieDao{
    @Override
    public int queryCountBySearch(ConfigPropertieSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ConfigPropertie.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ConfigPropertie> queryListBySearch(ConfigPropertieSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ConfigPropertie.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, ConfigPropertieSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("setting_key",search.getSettingKey());

        sqlExpression.andEqNoEmpty("setting_value",search.getSettingValue());

        sqlExpression.andEqNoEmpty("application",search.getApplication());

        sqlExpression.andEqNoEmpty("profile",search.getProfile());

        sqlExpression.andEqNoEmpty("label",search.getLabel());

    }
}