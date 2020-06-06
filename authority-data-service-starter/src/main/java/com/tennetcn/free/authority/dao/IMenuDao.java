package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.entity.model.Menu;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuRoute;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuTree;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IMenuDao extends ISuperDao<Menu> {

    int queryCountBySearch(MenuSearch search);

    List<MenuTree> queryListTreeBySearch(MenuSearch search);

    MenuTree queryModelTree(String id);

    List<MenuRoute> queryMenuRouteByRoleIds(List<String> roleIds);

    List<MenuRoute> queryMenuRouteByRGIds(List<String> roleIds, List<String> groupIds);

}
