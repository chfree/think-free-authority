package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.model.Menu;
import com.cditer.free.authority.data.entity.viewmodel.MenuRoute;
import com.cditer.free.authority.data.entity.viewmodel.MenuSearch;
import com.cditer.free.authority.data.entity.viewmodel.MenuTree;
import com.cditer.free.data.dao.base.ISuperService;

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

    List<Menu> queryListByServerName(String serverName);

    List<MenuRoute> queryMenuRouteByRoleIds(List<String> roleIds);

    List<MenuRoute> queryMenuRouteFormatByRoleIds(List<String> roleIds);

    List<MenuRoute> queryMenuRouteByRGIds(List<String> roleIds, List<String> groupIds);

    List<MenuRoute> queryMenuRouteFormatByRGIds(List<String> roleIds, List<String> groupIds);

    List<Menu> queryMenuBySeverNameAndRGIds(String serverName, List<String> roleIds, List<String> groupIds);
}
