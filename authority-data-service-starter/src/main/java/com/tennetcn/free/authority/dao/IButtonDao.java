package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.data.entity.model.Button;
import com.tennetcn.free.authority.data.entity.viewmodel.ButtonSearch;
import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IButtonDao extends ISuperDao<Button> {
    int queryCountBySearch(ButtonSearch search);

    List<Button> queryListBySearch(ButtonSearch search, PagerModel pagerModel);

    List<Button> queryListByRoleIds(List<String> roleIds);
}
