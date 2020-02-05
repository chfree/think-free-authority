package com.tennetcn.free.develop.dao.impl;

import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.develop.dao.ICodeTmpDao;
import com.tennetcn.free.develop.model.CodeTmp;
import com.tennetcn.free.develop.viewmodel.CodeTmpSearch;
import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:06:25
 * @comment     代码模板
 */

@Component
public class CodeTmpDaoImpl extends SuperDao<CodeTmp> implements ICodeTmpDao{
    @Override
    public int queryCountBySearch(CodeTmpSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(CodeTmp.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<CodeTmp> queryListBySearch(CodeTmpSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(CodeTmp.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    private void appendExpression(ISqlExpression sqlExpression, CodeTmpSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andEqNoEmpty("comment",search.getComment());

        sqlExpression.andEqNoEmpty("content",search.getContent());

        sqlExpression.andEqNoEmpty("pub",search.getPub());

        sqlExpression.andEqNoEmpty("type",search.getType());

        sqlExpression.andEqNoEmpty("create_user_id",search.getCreateUserId());

    }
}