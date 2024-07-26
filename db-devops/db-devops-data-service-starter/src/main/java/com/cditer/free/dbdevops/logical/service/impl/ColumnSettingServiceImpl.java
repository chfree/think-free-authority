package com.cditer.free.dbdevops.logical.service.impl;

import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.dbdevops.data.model.ColumnSetting;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingView;
import com.cditer.free.dbdevops.logical.mapper.IColumnSettingMapper;
import com.cditer.free.dbdevops.logical.service.IColumnSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-07-25 12:59:48
 * @comment     列信息配置
 */

@Component
public class ColumnSettingServiceImpl extends SuperService<ColumnSetting> implements IColumnSettingService {
    @Autowired
    IColumnSettingMapper columnSettingMapper;

    @Override
    public int queryCountBySearch(ColumnSettingSearch search) {
        return columnSettingMapper.queryCountBySearch(search);
    }

    @Override
    public List<ColumnSettingView> queryListViewBySearch(ColumnSettingSearch search, PagerModel pagerModel) {
        return columnSettingMapper.queryListViewBySearch(search,pagerModel);
    }

    @Override
    public ColumnSettingView queryModelViewBySearch(ColumnSettingSearch search) {
        return columnSettingMapper.queryModelViewBySearch(search);
    }

    @Override
    public ColumnSettingView queryModelViewById(String id) {
        ColumnSettingSearch search = new ColumnSettingSearch();
        search.setId(id);
        return columnSettingMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveColumnSetting(ColumnSetting columnSetting) {
        columnSetting.autoPkIdAndStatus();

        return applyChange(columnSetting);
    }

}
