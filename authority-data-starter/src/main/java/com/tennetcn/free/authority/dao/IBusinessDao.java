package com.tennetcn.free.authority.dao;

import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.data.message.PagerModel;

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
