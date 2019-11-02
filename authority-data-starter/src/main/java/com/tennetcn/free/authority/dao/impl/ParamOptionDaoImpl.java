package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IParamOptionDao;
import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.authority.viewmodel.ParamOptionView;
import com.tennetcn.free.core.enums.OrderEnum;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
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
public class ParamOptionDaoImpl extends SuperDao<ParamOption> implements IParamOptionDao {

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

    @Override
    public List<ParamOption> queryListByDefineName(String defineName) {
        ISqlExpression defineIdSql = SqlExpressionFactory.createExpression().select("id").from(ParamDefine.class).andEq("name",defineName);

        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(ParamOption.class)
                .andWhereIn("define_id",defineIdSql)
                .addOrder("sort_code", OrderEnum.asc);

        return queryList(sqlExpression);
    }

    @Override
    public List<ParamOptionView> queryListByDefineNames(List<String> defineNames) {
        if(defineNames == null || defineNames.size()<=0){
            return null;
        }

        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("po.*")
                .appendSelect("pd.name as defineName")
                .from(ParamOption.class,"po")
                .leftJoin(ParamDefine.class,"pd").on("po.define_id","pd.id")
                .andWhereInString("pd.name",defineNames)
                .addOrder("sort_code", OrderEnum.asc);

        return queryList(sqlExpression,ParamOptionView.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, ParamOptionSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("define_id",search.getDefineId());

        sqlExpression.andEqNoEmpty("text",search.getText());

        sqlExpression.andEqNoEmpty("value",search.getValue());
    }
}
