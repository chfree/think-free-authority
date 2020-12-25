package com.tennetcn.free.authority.logical.service.impl;

import com.tennetcn.free.authority.logical.dao.IButtonDao;
import com.tennetcn.free.authority.data.entity.model.Button;
import com.tennetcn.free.authority.logical.service.IButtonService;
import com.tennetcn.free.authority.data.entity.viewmodel.ButtonSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
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

    @Override
    public List<Button> queryListByRGds(List<String> roleIds, List<String> groupIds) {
        return buttonDao.queryListByRGds(roleIds,groupIds);
    }
}
