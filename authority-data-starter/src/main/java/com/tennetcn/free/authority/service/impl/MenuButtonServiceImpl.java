package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.Menu;
import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.service.IMenuButtonService;
import com.tennetcn.free.authority.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.viewmodel.MenuButtonTree;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        if(pagerModel!=null){
            return queryList(sqlExpression,pagerModel);
        }
        return queryList(sqlExpression);
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

    @Override
    public List<MenuButtonTree> queryMenuButtonTreeList() {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Menu.class)
                .addOrder("sort_code", OrderEnum.asc);

        return queryList(sqlExpression, MenuButtonTree.class);
    }

    @Override
    public List<MenuButtonTree> queryListTreeFormat() {
        List<MenuButtonTree> allMenuButtonTrees = queryMenuButtonTreeList();

        List<MenuButton> allMenuButtons = this.queryList();

        if(allMenuButtonTrees==null||allMenuButtonTrees.size()<=0){
            return null;
        }
        // 默认顶级是0
        Integer minLevel = 0;

        // 在求出顶级所有的顶级部门
        var minMenuTrees = allMenuButtonTrees.stream().filter(menu -> menu.getLevel() == minLevel).collect(Collectors.toList());

        // 进行递归循环
        for (MenuButtonTree menuButtonTree: minMenuTrees) {
            menuTreeLoop(menuButtonTree,allMenuButtonTrees,allMenuButtons);
        }

        return minMenuTrees;
    }

    private void menuTreeLoop(MenuButtonTree currentMenuButtonTree,List<MenuButtonTree> allMenuTrees,List<MenuButton> allMenuButtons){

        var childrenMenuButtonTree = allMenuTrees.stream().filter(menu-> currentMenuButtonTree.getId().equals(menu.getParentId())).collect(Collectors.toList());
        currentMenuButtonTree.setChildren(childrenMenuButtonTree);

        var currentMenuButtons = allMenuButtons.stream().filter(menuButton -> currentMenuButtonTree.getId().equals(menuButton.getMenuId())).collect(Collectors.toList());
        currentMenuButtonTree.setMenuButtons(currentMenuButtons);

        for (MenuButtonTree menuButtonTree:childrenMenuButtonTree) {
            menuTreeLoop(menuButtonTree,allMenuTrees,allMenuButtons);
        }

    }
}
