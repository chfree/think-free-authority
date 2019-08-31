package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.viewmodel.MenuButtonTree;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.data.message.PagerModel;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IMenuButtonDao extends ISuperDao<MenuButton> {
    int queryCountBySearch(MenuButtonSearch search);

    List<MenuButton> queryListBySearch(MenuButtonSearch search, PagerModel pagerModel);

    boolean deleteByMenuId(String menuId);

    List<MenuButtonTree> queryMenuButtonTreeList();

}
