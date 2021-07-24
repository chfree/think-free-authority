package com.cditer.free.authority.logical.dao;

import com.cditer.free.authority.data.entity.viewmodel.BusinessSearch;
import com.cditer.free.authority.data.entity.model.Business;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IBusinessDao extends ISuperDao<Business> {
    int queryCountBySearch(BusinessSearch search);

    List<Business> queryListBySearch(BusinessSearch search, PagerModel pagerModel);
}
