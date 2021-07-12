package com.tennetcn.free.authority.logical.dao;

import com.tennetcn.free.authority.data.entity.model.MenuButton;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuButtonSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuButtonTree;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

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
