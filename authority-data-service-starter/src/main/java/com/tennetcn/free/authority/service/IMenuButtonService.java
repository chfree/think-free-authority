package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.data.model.MenuButton;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.data.viewmodel.MenuButtonTree;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IMenuButtonService extends ISuperService<MenuButton> {
    int queryCountBySearch(MenuButtonSearch search);

    List<MenuButton> queryListBySearch(MenuButtonSearch search, PagerModel pagerModel);

    boolean saveMenuButtons(String menuId,List<MenuButton> menuButtons);

    boolean deleteByMenuId(String menuId);

    List<MenuButtonTree> queryMenuButtonTreeList();

    List<MenuButtonTree> queryListTreeFormat();
}
