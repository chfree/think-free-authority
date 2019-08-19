package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.service.IMenuButtonService;
import com.tennetcn.free.authority.viewmodel.MenuButtonSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
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
public class MenuButtonServiceImpl extends SuperService<MenuButton> implements IMenuButtonService {

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

        return queryList(sqlExpression,pagerModel);
    }

    @Override
    public boolean saveMenuButtons(String menuId,List<MenuButton> menuButtons) {
        if(!deleteByMenuId(menuId)){
            return false;
        }
        if(menuButtons==null||menuButtons.size()<=0){
            return true;
        }
        return insertListEx(menuButtons)==menuButtons.size();
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
}
