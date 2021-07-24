package com.cditer.free.develop.service.impl;

import com.cditer.free.develop.dao.IDictTableDao;
import com.cditer.free.develop.service.IDictTableService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.develop.data.entity.model.DictTable;
import com.cditer.free.develop.data.entity.viewmodel.DictTableSearch;
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
