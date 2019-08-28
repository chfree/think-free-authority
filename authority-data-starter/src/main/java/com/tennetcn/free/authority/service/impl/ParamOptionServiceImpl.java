package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.service.IParamOptionService;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
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
public class ParamOptionServiceImpl extends SuperService<ParamOption> implements IParamOptionService {

    @Override
    public int queryCountBySearch(ParamOptionSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(ParamOption.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<ParamOption> queryListBySearch(ParamOptionSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ParamOption.class)
                     .addOrder("sort_code", OrderEnum.asc);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }


    private void appendExpression(ISqlExpression sqlExpression, ParamOptionSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("define_id",search.getDefineId());

        sqlExpression.andEqNoEmpty("text",search.getText());

        sqlExpression.andEqNoEmpty("value",search.getValue());
    }
}
