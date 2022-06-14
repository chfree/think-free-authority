package com.cditer.free.behavior.dao.impl;

import com.cditer.free.behavior.dao.IWebVisitLogDao;
import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-14 12:16:15
 * @comment     操作日志表
 */

@Component
public class WebVisitLogDaoImpl extends SuperDao<WebVisitLog> implements IWebVisitLogDao {
    @Override
    public int queryCountBySearch(WebVisitLogSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(WebVisitLog.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<WebVisitLog> queryListBySearch(WebVisitLogSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(WebVisitLog.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, WebVisitLogSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("bsn_id",search.getBsnId());

        sqlExpression.andEqNoEmpty("user_id",search.getUserId());

        sqlExpression.andEqNoEmpty("role_id",search.getRoleId());

        sqlExpression.andEqNoEmpty("mark1",search.getMark1());

        sqlExpression.andEqNoEmpty("mark2",search.getMark2());

        sqlExpression.andEqNoEmpty("mark3",search.getMark3());

        sqlExpression.andEqNoEmpty("mark4",search.getMark4());

    }
}
