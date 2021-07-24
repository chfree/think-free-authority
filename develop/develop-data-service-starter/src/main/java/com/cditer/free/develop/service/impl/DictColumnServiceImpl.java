package com.cditer.free.develop.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.develop.dao.IDictColumnDao;
import com.cditer.free.develop.service.IDictColumnService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.develop.data.entity.model.DictColumn;
import com.cditer.free.develop.data.entity.viewmodel.DictColumnSearch;
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

        dictColumnFormat(dictColumns,tableId);

        return insertListEx(dictColumns) == dictColumns.size();
    }

    private void dictColumnFormat(List<DictColumn> dictColumns,String tableId){
        int index = 0;
        for (DictColumn dictColumn:dictColumns) {
            dictColumn.setSortCode(++index);
            dictColumn.setTableId(tableId);
            dictColumn.setId(IdUtil.randomUUID());
        }
    }


    @Override
    public boolean deleteByTableId(String tableId) {
        return dictColumnDao.deleteByTableId(tableId)>=0;
    }
}
