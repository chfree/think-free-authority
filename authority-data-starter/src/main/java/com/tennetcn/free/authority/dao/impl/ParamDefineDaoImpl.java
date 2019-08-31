package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IParamDefineDao;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.service.IParamDefineService;
import com.tennetcn.free.authority.viewmodel.ParamDefineSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
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
                     .addOrder("name", OrderEnum.asc);

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
