package com.cditer.free.develop.dao;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperDao;
import com.cditer.free.develop.data.entity.model.DictTable;
import com.cditer.free.develop.data.entity.viewmodel.DictTableSearch;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:49
 * @comment
 */

public interface IDictTableDao extends ISuperDao<DictTable> {
    int queryCountBySearch(DictTableSearch search);

    List<DictTable> queryListBySearch(DictTableSearch search, PagerModel pagerModel);
}
