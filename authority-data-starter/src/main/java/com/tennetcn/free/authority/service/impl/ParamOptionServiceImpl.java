package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.ParamDefine;
import com.tennetcn.free.authority.model.ParamOption;
import com.tennetcn.free.authority.service.IParamOptionService;
import com.tennetcn.free.authority.viewmodel.ParamOptionSearch;
import com.tennetcn.free.authority.viewmodel.ParamOptionView;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Object, List<ParamOption>> queryListGroupByDefineNames(List<String> defineNames) {
        if(defineNames == null || defineNames.size()<=0){
            return null;
        }

        List<ParamOptionView> paramOptionViews = queryListByDefineNames(defineNames);
        // 进行分组
        Map<Object, List<ParamOption>> resultMap = new HashMap<>();
        for (ParamOptionView paramOptionView : paramOptionViews) {
            resultMap.computeIfAbsent(paramOptionView.getDefineName(), k -> new ArrayList<>()).add(paramOptionView);
        }

        return resultMap;
    }

    private void appendExpression(ISqlExpression sqlExpression, ParamOptionSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andEqNoEmpty("define_id",search.getDefineId());

        sqlExpression.andEqNoEmpty("text",search.getText());

        sqlExpression.andEqNoEmpty("value",search.getValue());
    }
}
