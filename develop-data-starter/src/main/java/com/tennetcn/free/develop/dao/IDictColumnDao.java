package com.tennetcn.free.develop.dao;

import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperDao;
import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.develop.viewmodel.DictColumnSearch;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:49
 * @comment
 */

public interface IDictColumnDao extends ISuperDao<DictColumn> {
    int queryCountBySearch(DictColumnSearch search);

    List<DictColumn> queryListBySearch(DictColumnSearch search, PagerModel pagerModel);
}
