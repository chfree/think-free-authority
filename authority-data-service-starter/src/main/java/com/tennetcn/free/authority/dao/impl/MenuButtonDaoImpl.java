package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IMenuButtonDao;
import com.tennetcn.free.authority.data.model.Menu;
import com.tennetcn.free.authority.data.model.MenuButton;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonTree;
import com.tennetcn.free.core.enums.OrderEnum;
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
public class MenuButtonDaoImpl extends SuperDao<MenuButton> implements IMenuButtonDao {

    @Override
    public int queryCountBySearch(MenuButtonSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(MenuButton.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<MenuButton> queryListBySearch(MenuButtonSearch search, PagerModel pagerModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(MenuButton.class);

        appendExpression(sqlExpression,search);
        if(pagerModel!=null){
            return queryList(sqlExpression,pagerModel);
        }
        return queryList(sqlExpression);
    }

    @Override
    public boolean deleteByMenuId(String menuId) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete().from(MenuButton.class)
                     .andEq("menu_id",menuId);

        return delete(sqlExpression) >= 0;
    }

    private void appendExpression(ISqlExpression sqlExpression, MenuButtonSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andRightLikeNoEmpty("title", search.getLikeTitle());

        sqlExpression.andEqNoEmpty("menu_id",search.getMenuId());
    }

    @Override
    public List<MenuButtonTree> queryMenuButtonTreeList() {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Menu.class)
                .addOrder("sort_code", OrderEnum.asc);

        return queryList(sqlExpression, MenuButtonTree.class);
    }
}
