package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IButtonDao;
import com.tennetcn.free.authority.enums.RoleFuncType;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
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
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.select("button.id,button.name,button.icon,button.theme,button.title,button.button_type")
                .from(RoleFunc.class,"roleFunc")
                .leftJoin(MenuButton.class,"button").on("roleFunc.func_id","button.id")
                .andWhereInString("role_id",roleIds)
                .andEq("func_type", RoleFuncType.BUTTON);

        return queryList(sqlExpression,Button.class);
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
