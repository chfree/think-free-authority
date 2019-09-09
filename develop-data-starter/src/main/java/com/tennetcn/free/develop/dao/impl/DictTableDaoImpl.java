package com.tennetcn.free.develop.dao.impl;

import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import com.tennetcn.free.develop.dao.IDictTableDao;
import com.tennetcn.free.develop.model.DictTable;
import com.tennetcn.free.develop.viewmodel.DictTableSearch;
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
