package com.tennetcn.free.authority.service;

import com.tennetcn.free.authority.model.Business;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.data.dao.base.ISuperService;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 23:04
 * @comment
 */

public interface IBusinessService extends ISuperService<Business> {
    int queryCountBySearch(BusinessSearch search);
}
