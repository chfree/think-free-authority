package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.Menu;
import com.tennetcn.free.authority.viewmodel.MenuRoute;
import com.tennetcn.free.authority.viewmodel.MenuSearch;
import com.tennetcn.free.authority.viewmodel.MenuTree;
import com.tennetcn.free.data.dao.base.ISuperService;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IMenuService extends ISuperService<Menu> {

    int queryCountBySearch(MenuSearch search);

    List<MenuTree> queryListTreeBySearch(MenuSearch search);

    List<MenuTree> queryListTreeFormat(MenuSearch search);

    MenuTree queryModelTree(String id);

    List<MenuRoute> queryMenuRouteByRoleIds(List<String> roleIds);

    List<MenuRoute> queryMenuRouteFormatByRoleIds(List<String> roleIds);
}
