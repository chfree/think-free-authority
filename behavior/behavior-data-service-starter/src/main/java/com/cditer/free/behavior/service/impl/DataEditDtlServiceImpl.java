package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.behavior.mapper.IDataEditDtlMapper;
import com.cditer.free.behavior.service.IDataEditDtlService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-07-02 21:53:24
 * @comment     数据修改详情
 */

@Component
public class DataEditDtlServiceImpl extends SuperService<DataEditDtl> implements IDataEditDtlService {

    @Autowired
    protected IDataEditDtlMapper dataEditDtlMapper;

    @Override
    public int queryCountBySearch(DataEditDtlSearch search) {
        return dataEditDtlMapper.queryCountBySearch(search);
    }

    @Override
    public List<DataEditDtlView> queryListViewBySearch(DataEditDtlSearch search, PagerModel pagerModel) {
        return dataEditDtlMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public DataEditDtlView queryModelViewBySearch(DataEditDtlSearch search) {
        return dataEditDtlMapper.queryModelViewBySearch(search);
    }

    @Override
    public DataEditDtlView queryModelViewById(String id) {
        DataEditDtlSearch search = new DataEditDtlSearch();
        search.setId(id);

        return dataEditDtlMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveDataEditDtl(DataEditDtl dataEditDtl) {
        dataEditDtl.autoPkIdAndStatus();

        return applyChange(dataEditDtl);
    }
}