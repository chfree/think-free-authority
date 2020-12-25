package com.tennetcn.free.authority.logical.dao.impl;

import com.tennetcn.free.authority.logical.dao.IButtonDao;
import com.tennetcn.free.authority.data.entity.model.Button;
import com.tennetcn.free.authority.data.entity.model.GroupFunc;
import com.tennetcn.free.authority.data.entity.model.MenuButton;
import com.tennetcn.free.authority.data.entity.model.RoleFunc;
import com.tennetcn.free.authority.data.entity.viewmodel.ButtonSearch;
import com.tennetcn.free.authority.data.enums.MenuFuncType;
import com.tennetcn.free.core.message.data.PagerModel;
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
public class ButtonDaoImpl extends SuperDao<Button> implements IButtonDao {

    @Override
    public int queryCountBySearch(ButtonSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Button.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<Button> queryListBySearch(ButtonSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Button.class);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public List<Button> queryListByRoleIds(List<String> roleIds) {
        if(roleIds==null||roleIds.isEmpty()){
            return null;
        }
        return queryList(roleSql(roleIds));
    }

    private ISqlExpression roleSql(List<String> roleIds){
        if(roleIds==null||roleIds.isEmpty()){
            return null;
        }
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("button.id,button.name,button.icon,button.theme,button.title,button.button_type")
                .from(RoleFunc.class,"roleFunc")
                .leftJoin(MenuButton.class,"button").on("roleFunc.func_id","button.id")
                .andWhereInString("role_id",roleIds)
                .andEq("func_type", MenuFuncType.BUTTON);

        return sqlExpression;
    }

    private ISqlExpression groupSql(List<String> groupIds){
        if(groupIds==null||groupIds.isEmpty()){
            return null;
        }
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("button.id,button.name,button.icon,button.theme,button.title,button.button_type")
                .from(GroupFunc.class,"groupFunc")
                .leftJoin(MenuButton.class,"button").on("groupFunc.func_id","button.id")
                .andWhereInString("group_id",groupIds)
                .andEq("func_type", MenuFuncType.BUTTON);

        return sqlExpression;
    }

    @Override
    public List<Button> queryListByRGds(List<String> roleIds, List<String> groupIds) {
        ISqlExpression roleSqlExpression = roleSql(roleIds);
        ISqlExpression groupSqlExpression = groupSql(groupIds);

        // 同为null，则返回null
        if(roleSqlExpression==null&&groupSqlExpression==null){
            return null;
        }

        // group为null则返回role
        if(groupSqlExpression==null){
            return queryList(roleSqlExpression);
        }

        // role为null则返回group
        if(roleSqlExpression==null){
            return queryList(groupSqlExpression);
        }

        // 同不为null，则进行union
        ISqlExpression unionSqlExpression = SqlExpressionFactory.createExpression();
        unionSqlExpression.union(roleSqlExpression, groupSqlExpression);

        return queryList(unionSqlExpression);
    }

    private void appendExpression(ISqlExpression sqlExpression, ButtonSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andRightLikeNoEmpty("title", search.getLikeTitle());
    }
}
