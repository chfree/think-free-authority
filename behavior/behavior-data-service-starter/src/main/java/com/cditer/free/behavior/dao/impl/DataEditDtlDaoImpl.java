package com.cditer.free.behavior.dao.impl;

import com.cditer.free.behavior.dao.IDataEditDtlDao;
import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-04-27 21:10:31
 * @comment     数据修改详情
 */

@Component
public class DataEditDtlDaoImpl extends SuperDao<DataEditDtl> implements IDataEditDtlDao {
    @Override
    public int queryCountBySearch(DataEditDtlSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(DataEditDtl.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<DataEditDtl> queryListBySearch(DataEditDtlSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(DataEditDtl.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, DataEditDtlSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("edit_id",search.getEditId());

        sqlExpression.andEqNoEmpty("pro_name",search.getProName());

        sqlExpression.andEqNoEmpty("pro_text",search.getProText());

        sqlExpression.andEqNoEmpty("new_val",search.getNewVal());

        sqlExpression.andEqNoEmpty("old_val",search.getOldVal());

    }
}
