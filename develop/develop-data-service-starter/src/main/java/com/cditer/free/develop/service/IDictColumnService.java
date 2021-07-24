package com.cditer.free.develop.service;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.ISuperService;
import com.cditer.free.develop.data.entity.model.DictColumn;
import com.cditer.free.develop.data.entity.viewmodel.DictColumnSearch;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:52
 * @comment
 */

public interface IDictColumnService extends ISuperService<DictColumn> {
    int queryCountBySearch(DictColumnSearch search);

    List<DictColumn> queryListBySearch(DictColumnSearch search, PagerModel pagerModel);

    boolean saveDictColumns(String tableId,List<DictColumn> dictColumns);

    boolean deleteByTableId(String tableId);
}
