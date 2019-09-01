package com.tennetcn.free.develop.service.impl;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.dao.base.impl.SuperService;
import com.tennetcn.free.develop.dao.IDictColumnDao;
import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.develop.service.IDictColumnService;
import com.tennetcn.free.develop.viewmodel.DictColumnSearch;
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
public class DictColumnServiceImpl extends SuperService<DictColumn> implements IDictColumnService {

    @Autowired
    IDictColumnDao dictColumnDao;

    @Override
    public int queryCountBySearch(DictColumnSearch search) {
        return dictColumnDao.queryCountBySearch(search);
    }

    @Override
    public List<DictColumn> queryListBySearch(DictColumnSearch search, PagerModel pagerModel) {
        return dictColumnDao.queryListBySearch(search,pagerModel);
    }

    @Override
    public boolean saveDictColumns(String tableId, List<DictColumn> dictColumns) {
        if(!deleteByTableId(tableId)){
            return false;
        }
        if(dictColumns==null||dictColumns.size()<=0){
            return true;
        }

        dictColumns.forEach(item->{
            item.setTableId(tableId);
            item.setId(IdUtil.randomUUID());
        });

        return insertListEx(dictColumns) == dictColumns.size();
    }

    @Override
    public boolean deleteByTableId(String tableId) {
        return dictColumnDao.deleteByTableId(tableId)>=0;
    }
}
