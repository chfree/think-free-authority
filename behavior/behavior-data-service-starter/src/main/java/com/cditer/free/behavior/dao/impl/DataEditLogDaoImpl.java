package com.cditer.free.behavior.dao.impl;

import com.cditer.free.behavior.dao.IDataEditLogDao;
import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:05:14
 * @comment     数据修改记录
 */

@Component
public class DataEditLogDaoImpl extends SuperDao<DataEditLog> implements IDataEditLogDao {
    @Override
    public int queryCountBySearch(DataEditLogSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(DataEditLog.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<DataEditLog> queryListBySearch(DataEditLogSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(DataEditLog.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, DataEditLogSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("bsn_type",search.getBsnType());

        sqlExpression.andEqNoEmpty("bsn_id",search.getBsnId());

        sqlExpression.andEqNoEmpty("oper_type",search.getOperType());

        sqlExpression.andEqNoEmpty("user_dept_id",search.getUserDeptId());

        sqlExpression.andEqNoEmpty("user_dept_name",search.getUserDeptName());

        sqlExpression.andEqNoEmpty("user_role_id",search.getUserRoleId());

        sqlExpression.andEqNoEmpty("user_role_name",search.getUserRoleName());

    }
}
