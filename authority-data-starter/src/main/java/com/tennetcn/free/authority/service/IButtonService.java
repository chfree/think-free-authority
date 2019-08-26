package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.data.message.PagerModel;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IButtonService extends ISuperService<Button> {
    int queryCountBySearch(ButtonSearch search);

    List<Button> queryListBySearch(ButtonSearch search, PagerModel pagerModel);

    List<Button> queryListByRoleIds(List<String> roleIds);
}
