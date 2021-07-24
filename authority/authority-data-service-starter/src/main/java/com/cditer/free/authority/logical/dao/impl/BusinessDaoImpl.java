package com.cditer.free.authority.logical.dao.impl;

import com.cditer.free.authority.data.entity.viewmodel.BusinessSearch;
import com.cditer.free.authority.logical.dao.IBusinessDao;
import com.cditer.free.authority.data.entity.model.Business;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:12
 * @comment
 */

@Component
public class BusinessDaoImpl extends SuperDao<Business> implements IBusinessDao {
    @Override
    public int queryCountBySearch(BusinessSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Business.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Business> queryListBySearch(BusinessSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Business.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression,BusinessSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());
    }
}
