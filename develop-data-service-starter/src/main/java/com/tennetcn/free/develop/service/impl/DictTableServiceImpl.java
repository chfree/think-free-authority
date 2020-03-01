package com.tennetcn.free.develop.service.impl;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.develop.dao.IDictTableDao;
import com.tennetcn.free.develop.data.entity.model.DictTable;
import com.tennetcn.free.develop.service.IDictTableService;
import com.tennetcn.free.develop.data.entity.viewmodel.DictTableSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 23:52
 * @comment
 */

@Component
public class DictTableServiceImpl extends SuperService<DictTable> implements IDictTableService {

    @Autowired
    IDictTableDao dictTableDao;

    @Override
    public int queryCountBySearch(DictTableSearch search) {
        return dictTableDao.queryCountBySearch(search);
    }

    @Override
    public List<DictTable> queryListBySearch(DictTableSearch search, PagerModel pagerModel) {
        return dictTableDao.queryListBySearch(search,pagerModel);
    }
}
