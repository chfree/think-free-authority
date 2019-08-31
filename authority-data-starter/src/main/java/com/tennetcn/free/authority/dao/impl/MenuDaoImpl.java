package com.tennetcn.free.authority.dao.impl;

import com.tennetcn.free.authority.dao.IMenuDao;
import com.tennetcn.free.authority.enums.MenuType;
import com.tennetcn.free.authority.enums.RoleFuncType;
import com.tennetcn.free.authority.model.Menu;
import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.service.IMenuService;
import com.tennetcn.free.authority.viewmodel.MenuRoute;
import com.tennetcn.free.authority.viewmodel.MenuSearch;
import com.tennetcn.free.authority.viewmodel.MenuTree;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperDao;
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
public class MenuDaoImpl extends SuperDao<Menu> implements IMenuDao {

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
    public MenuTree queryModelTree(String id) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.selectAllFrom(Menu.class,"menu")
                .appendSelect("parent.title as parentName")
                .leftJoin(Menu.class,"parent")
                .on("menu.parent_id","parent.id")
                .andEq("menu.id",id);

        return queryModel(sqlExpression, MenuTree.class);
    }

    @Override
    public List<MenuRoute> queryMenuRouteByRoleIds(List<String> roleIds) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();

        if(roleIds!=null&&roleIds.size()>0){
            sqlExpression.select("menu.id,menu.name,menu.title,menu.icon,menu.hidden,menu.path,menu.page_path,menu.type,menu.use_type,menu.parent_id,menu.theme,menu.sort_code,menu.delete_mark,menu.comments,menu.menu_mark,menu.level")
                     .from(RoleFunc.class,"roleFunc")
                     .leftJoin(Menu.class,"menu").on("roleFunc.func_id","menu.id")
                     .andWhereInString("role_id",roleIds)
                     .andEq("func_type", RoleFuncType.MENU)
                    .andEq("menu.type", MenuType.MENU)
                     .addOrder("menu.sort_code",OrderEnum.asc);;
        }else{
            sqlExpression.selectAllFrom(Menu.class)
            .addOrder("sort_code",OrderEnum.asc);
        }

        return queryList(sqlExpression,MenuRoute.class);
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
