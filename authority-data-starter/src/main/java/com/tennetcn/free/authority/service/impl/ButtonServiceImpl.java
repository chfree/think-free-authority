package com.tennetcn.free.authority.service.impl;

import com.tennetcn.free.authority.dao.IButtonDao;
import com.tennetcn.free.authority.enums.RoleFuncType;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.service.IButtonService;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.data.dao.base.ISqlExpression;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.data.utils.SqlExpressionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:13
 * @comment
 */

@Component
public class ButtonServiceImpl extends SuperService<Button> implements IButtonService {

    @Autowired
    IButtonDao buttonDao;

    @Override
    public int queryCountBySearch(ButtonSearch search) {
        return buttonDao.queryCountBySearch(search);
    }

    @Override
    public List<Button> queryListBySearch(ButtonSearch search, PagerModel pagerModel) {
        return buttonDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public List<Button> queryListByRoleIds(List<String> roleIds) {
        return buttonDao.queryListByRoleIds(roleIds);
    }
}
