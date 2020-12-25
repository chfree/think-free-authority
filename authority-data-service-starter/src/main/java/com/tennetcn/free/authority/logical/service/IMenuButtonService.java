package com.tennetcn.free.authority.logical.service;

import com.tennetcn.free.authority.data.entity.model.MenuButton;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuButtonTree;
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
