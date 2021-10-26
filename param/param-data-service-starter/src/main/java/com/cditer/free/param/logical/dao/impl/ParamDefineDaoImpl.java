package com.cditer.free.param.logical.dao.impl;

import com.cditer.free.param.data.entity.model.ParamDefine;
import com.cditer.free.param.data.entity.viewmodel.ParamDefineSearch;
import com.cditer.free.param.logical.dao.IParamDefineDao;
import com.cditer.free.core.enums.OrderEnum;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.dao.base.impl.SuperDao;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class ParamDefineDaoImpl extends SuperDao<ParamDefine> implements IParamDefineDao {

    @Override
    public int queryCountBySearch(ParamDefineSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ParamDefine.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ParamDefine> queryListBySearch(ParamDefineSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ParamDefine.class)
                     .addOrder("name", OrderEnum.ASC);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }


    private void appendExpression(ISqlExpression sqlExpression, ParamDefineSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andRightLikeNoEmpty("title", search.getLikeTitle());
    }
}
