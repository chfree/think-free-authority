package com.cditer.free.develop.dao.impl;

import com.cditer.free.develop.dao.ICodeTmpDao;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.develop.data.entity.model.CodeTmp;
import com.cditer.free.develop.data.entity.viewmodel.CodeTmpSearch;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:06:25
 * @comment     代码模板
 */

@Component
public class CodeTmpDaoImpl extends SuperDao<CodeTmp> implements ICodeTmpDao {
    @Override
    public int queryCountBySearch(CodeTmpSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(CodeTmp.class);

        appendExpression(sqlExpression,search);
        appendSqlByCreateUserId(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public int queryCountByCheck(CodeTmpSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(CodeTmp.class);

        appendExpression(sqlExpression,search);

        sqlExpression.andEq("create_user_id",search.getCreateUserId());

        return queryCount(sqlExpression);
    }

    @Override
    public List<CodeTmp> queryListBySearch(CodeTmpSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(CodeTmp.class);

        appendExpression(sqlExpression,search);
        appendSqlByCreateUserId(sqlExpression,search);

        sqlExpression.addOrder("pub", OrderEnum.asc)
                     .addOrder("name",OrderEnum.asc)
                     .addOrder("group_name",OrderEnum.asc);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendSqlByCreateUserId(ISqlExpression sqlExpression,CodeTmpSearch search){
        sqlExpression.andWhere("(create_user_id=#{createUserId} or pub='02')");
        sqlExpression.setParam("createUserId",search.getCreateUserId());
    }

    private void appendExpression(ISqlExpression sqlExpression, CodeTmpSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("pub",search.getPub());

        sqlExpression.andEqNoEmpty("type",search.getType());

        sqlExpression.andEqNoEmpty("group_name",search.getGroupName());

        sqlExpression.andLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andLikeNoEmpty("type",search.getLikeType());

        sqlExpression.andLikeNoEmpty("group_name",search.getLikeGroupName());
    }
}