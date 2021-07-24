package com.cditer.free.authority.logical.service;

import com.cditer.free.authority.data.entity.viewmodel.ButtonSearch;
import com.cditer.free.authority.data.entity.model.Button;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;

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

    List<Button> queryListByRGds(List<String> roleIds,List<String> groupIds);
}
