package com.cditer.free.behavior.dao.impl;

import com.cditer.free.behavior.dao.IWebVisitLimitDao;
import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLimitSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-15 12:51:05
 * @comment     web访问控制表
 */

@Component
public class WebVisitLimitDaoImpl extends SuperDao<WebVisitLimit> implements IWebVisitLimitDao {
    @Override
    public int queryCountBySearch(WebVisitLimitSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(WebVisitLimit.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<WebVisitLimit> queryListBySearch(WebVisitLimitSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(WebVisitLimit.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, WebVisitLimitSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("visit_type",search.getVisitType());

        sqlExpression.andEqNoEmpty("limit_type",search.getLimitType());

        sqlExpression.andEqNoEmpty("max_count",search.getMaxCount());

        sqlExpression.andEqNoEmpty("status",search.getStatus());
    }
}
