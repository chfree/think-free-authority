package com.tennetcn.free.develop.dao.impl;

import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import com.tennetcn.free.develop.dao.IDictColumnDao;
import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.develop.viewmodel.DictColumnSearch;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:50
 * @comment
 */

@Component
public class DictColumnDaoImpl extends SuperDao<DictColumn> implements IDictColumnDao {
    @Override
    public int queryCountBySearch(DictColumnSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(DictColumn.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<DictColumn> queryListBySearch(DictColumnSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(DictColumn.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, DictColumnSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("table_id",search.getTableId());
    }
}
