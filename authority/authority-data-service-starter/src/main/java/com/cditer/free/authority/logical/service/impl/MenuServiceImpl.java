package com.cditer.free.authority.logical.service.impl;

import com.cditer.free.authority.data.entity.viewmodel.MenuRoute;
import com.cditer.free.authority.data.entity.viewmodel.MenuSearch;
import com.cditer.free.authority.data.entity.viewmodel.MenuTree;
import com.cditer.free.authority.logical.dao.IMenuDao;
import com.cditer.free.authority.data.entity.model.Menu;
import com.cditer.free.authority.logical.service.IMenuService;
import com.cditer.free.data.dao.base.impl.SuperService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IMenuDao menuDao;

    @Override
    public int queryCountBySearch(MenuSearch search) {
        return menuDao.queryCountBySearch(search);
    }

    @Override
    public List<MenuTree> queryListTreeBySearch(MenuSearch search) {
        return menuDao.queryListTreeBySearch(search);
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
        return menuDao.queryModelTree(id);
    }

    @Override
    public List<Menu> queryListByServerName(String serverName) {
        Menu search = new Menu();
        search.setDeleteMark(null);
        search.setSortCode(null);
        search.setServerName(serverName);

        return queryList(search);
    }

    @Override
    public List<MenuRoute> queryMenuRouteByRoleIds(List<String> roleIds) {
        return menuDao.queryMenuRouteByRoleIds(roleIds);
    }

    @Override
    public List<MenuRoute> queryMenuRouteFormatByRoleIds(List<String> roleIds) {
        List<MenuRoute> allMenuRoutes = queryMenuRouteByRoleIds(roleIds);

        if(allMenuRoutes==null||allMenuRoutes.size()<=0){
            return null;
        }

        int minLevel = 0;
        // 在求出顶级所有的顶级菜单
        var minMenuRoutes = allMenuRoutes.stream().filter(menu -> menu.getLevel() == minLevel).collect(Collectors.toList());

        // 进行递归循环
        for (MenuRoute menuRoute: minMenuRoutes) {
            menuRouteLoop(menuRoute,allMenuRoutes);
        }

        return minMenuRoutes;
    }

    @Override
    public List<MenuRoute> queryMenuRouteByRGIds(List<String> roleIds, List<String> groupIds) {
        return menuDao.queryMenuRouteByRGIds(roleIds,groupIds);
    }

    @Override
    public List<MenuRoute> queryMenuRouteFormatByRGIds(List<String> roleIds, List<String> groupIds) {
        List<MenuRoute> allMenuRoutes = queryMenuRouteByRGIds(roleIds, groupIds);

        if(allMenuRoutes==null||allMenuRoutes.size()<=0){
            return null;
        }

        int minLevel = 0;
        // 在求出顶级所有的顶级菜单
        var minMenuRoutes = allMenuRoutes.stream().filter(menu -> menu.getLevel() == minLevel).collect(Collectors.toList());

        // 进行递归循环
        for (MenuRoute menuRoute: minMenuRoutes) {
            menuRouteLoop(menuRoute,allMenuRoutes);
        }

        return minMenuRoutes;
    }

    @Override
    public List<Menu> queryMenuBySeverNameAndRGIds(String serverName, List<String> roleIds, List<String> groupIds) {
        return menuDao.queryMenuBySeverNameAndRGIds(serverName, roleIds, groupIds);
    }

    private void menuRouteLoop(MenuRoute currentMenuRoute,List<MenuRoute> allMenuRoutes){

        var childrenMenuRoute = allMenuRoutes.stream().filter(menu-> currentMenuRoute.getId().equals(menu.getParentId())).collect(Collectors.toList());

        currentMenuRoute.setChildren(childrenMenuRoute);
        for (MenuRoute menuRoute:childrenMenuRoute) {
            menuRouteLoop(menuRoute,allMenuRoutes);
        }

    }
}
