package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IParamSettingDao;
import com.tennetcn.free.authority.data.entity.model.ParamSetting;
import com.tennetcn.free.authority.data.entity.viewmodel.ParamSettingSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:27:57
 * @comment     参数配置表
 */

@Component
public class ParamSettingDaoImpl extends SuperDao<ParamSetting> implements IParamSettingDao {
    @Override
    public int queryCountBySearch(ParamSettingSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ParamSetting.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ParamSetting> queryListBySearch(ParamSettingSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ParamSetting.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public ParamSetting queryModelBySearch(ParamSettingSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ParamSetting.class);

        appendExpression(sqlExpression,search);

        return queryModel(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression, ParamSettingSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andEqNoEmpty("param_value",search.getParamValue());

        sqlExpression.andEqNoEmpty("comments",search.getComments());

    }
}