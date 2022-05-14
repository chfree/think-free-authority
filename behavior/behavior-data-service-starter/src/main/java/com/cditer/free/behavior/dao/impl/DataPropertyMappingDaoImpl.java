package com.cditer.free.behavior.dao.impl;

import com.cditer.free.behavior.dao.IDataPropertyMappingDao;
import com.cditer.free.behavior.entity.model.DataPropertyMapping;
import com.cditer.free.behavior.entity.viewmodel.DataPropertyMappingSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-05-14 10:26:14
 * @comment     属性映射表
 */

@Component
public class DataPropertyMappingDaoImpl extends SuperDao<DataPropertyMapping> implements IDataPropertyMappingDao {
    @Override
    public int queryCountBySearch(DataPropertyMappingSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(DataPropertyMapping.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<DataPropertyMapping> queryListBySearch(DataPropertyMappingSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(DataPropertyMapping.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, DataPropertyMappingSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("bsn_type",search.getBsnType());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andEqNoEmpty("cagetory",search.getCagetory());

        sqlExpression.andEqNoEmpty("convert",search.getConvert());

    }
}
