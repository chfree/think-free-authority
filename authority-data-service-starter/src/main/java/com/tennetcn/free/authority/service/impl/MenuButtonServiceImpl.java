package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IMenuButtonDao;
import com.tennetcn.free.authority.data.model.MenuButton;
import com.tennetcn.free.authority.service.IMenuButtonService;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonTree;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IMenuButtonDao menuButtonDao;

    @Override
    public int queryCountBySearch(MenuButtonSearch search) {
        return menuButtonDao.queryCountBySearch(search);
    }

    @Override
    public List<MenuButton> queryListBySearch(MenuButtonSearch search, PagerModel pagerModel) {
        return menuButtonDao.queryListBySearch(search,pagerModel);
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
        return menuButtonDao.deleteByMenuId(menuId);
    }

    @Override
    public List<MenuButtonTree> queryMenuButtonTreeList() {
        return menuButtonDao.queryMenuButtonTreeList();
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
