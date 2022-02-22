package com.cditer.free.develop.dao.impl;

import com.cditer.free.develop.dao.IDictColumnDao;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.develop.data.entity.model.DictColumn;
import com.cditer.free.develop.data.entity.viewmodel.DictColumnSearch;
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
        sqlExpression.selectAllFrom(DictColumn.class)
        .addOrder("sort_code", OrderEnum.ASC);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public int deleteByTableId(String tableId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete().from(DictColumn.class)
                     .andEq("table_id",tableId);
        return delete(sqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression, DictColumnSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("table_id",search.getTableId());
    }
}
