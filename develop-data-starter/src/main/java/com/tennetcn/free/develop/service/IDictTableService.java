package com.tennetcn.free.develop.service;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.develop.model.DictTable;
import com.tennetcn.free.develop.viewmodel.DictTableSearch;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:52
 * @comment
 */

public interface IDictTableService extends ISuperService<DictTable> {
    int queryCountBySearch(DictTableSearch search);

    List<DictTable> queryListBySearch(DictTableSearch search, PagerModel pagerModel);
}
