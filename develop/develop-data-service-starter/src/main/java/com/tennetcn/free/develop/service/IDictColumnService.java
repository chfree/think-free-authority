package com.tennetcn.free.develop.service;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.ISuperService;
import com.tennetcn.free.develop.data.entity.model.DictColumn;
import com.tennetcn.free.develop.data.entity.viewmodel.DictColumnSearch;

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
