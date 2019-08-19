package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.model.Menu;
import com.tennetcn.free.authority.service.IMenuService;
import com.tennetcn.free.authority.viewmodel.MenuSearch;
import com.tennetcn.free.authority.viewmodel.MenuTree;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.enums.OrderEnum;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class MenuServiceImpl extends SuperService<Menu> implements IMenuService {

    @Override
    public int queryCountBySearch(MenuSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectCount().from(Menu.class);

        appendExpression(sqlExpression,search);

        return queryCount(sqlExpression);
    }

    @Override
    public List<MenuTree> queryListTreeBySearch(MenuSearch search) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Menu.class)
                     .addOrder("sort_code", OrderEnum.asc);

        appendExpression(sqlExpression,search);

        return queryList(sqlExpression,MenuTree.class);
    }

    @Override
    public List<MenuTree> queryListTreeFormat(MenuSearch search) {
        List<MenuTree> allMenuTrees = queryListTreeBySearch(search);
        if(allMenuTrees==null||allMenuTrees.size()<=0){
            return null;
        }
        // 求出最顶级的level是多少
        // 默认顶级是0，考虑到搜索的情况，这里在求一下
        Integer minLevel = allMenuTrees.stream().min(Comparator.comparing(Menu::getLevel)).get().getLevel();

        // 在求出顶级所有的顶级部门
        var minMenuTrees = allMenuTrees.stream().filter(menu -> menu.getLevel() == minLevel).collect(Collectors.toList());

        // 进行递归循环
        for (MenuTree menuTree: minMenuTrees) {
            menuTreeLoop(menuTree,allMenuTrees);
        }

        return minMenuTrees;
    }

    private void menuTreeLoop(MenuTree currentMenuTree,List<MenuTree> allMenuTrees){

        var childrenMenuTree = allMenuTrees.stream().filter(menu-> currentMenuTree.getId().equals(menu.getParentId())).collect(Collectors.toList());

        currentMenuTree.setChildren(childrenMenuTree);
        for (MenuTree menuTree:childrenMenuTree) {
            menuTreeLoop(menuTree,allMenuTrees);
        }

    }

    @Override
    public MenuTree queryModelTree(String id) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Menu.class,"menu")
                .appendSelect("parent.title as parentName")
                .leftJoin(Menu.class,"parent")
                .on("menu.parent_id","parent.id")
                .andEq("menu.id",id);

        return queryModel(sqlExpression, MenuTree.class);
    }

    private void appendExpression(ISqlExpression sqlExpression, MenuSearch search){
        sqlExpression.andEqNoEmpty("id",search.getId());

        sqlExpression.andEqNoEmpty("name",search.getName());

        sqlExpression.andNotEqNoEmpty("id",search.getNotId());

        sqlExpression.andRightLikeNoEmpty("name",search.getLikeName());

        sqlExpression.andEqNoEmpty("title",search.getTitle());

        sqlExpression.andRightLikeNoEmpty("title",search.getLikeTitle());
    }
}
