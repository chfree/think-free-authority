package com.cditer.free.develop.dao.impl;

import com.cditer.free.develop.dao.IDictTableDao;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.develop.data.entity.model.DictTable;
import com.cditer.free.develop.data.entity.viewmodel.DictTableSearch;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:50
 * @comment
 */

@Component
public class DictTableDaoImpl extends SuperDao<DictTable> implements IDictTableDao {
    @Override
    public int queryCountBySearch(DictTableSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(DictTable.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<DictTable> queryListBySearch(DictTableSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(DictTable.class)
        .addOrder("sort_code", OrderEnum.asc);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, DictTableSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("dbname",search.getDbname());
    }
}
