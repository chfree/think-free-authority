package com.cditer.free.param.logical.service.impl;

import com.cditer.free.param.data.entity.model.ColumnSetting;
import com.cditer.free.param.data.entity.viewmodel.ColumnSettingSearch;
import com.cditer.free.param.data.entity.viewmodel.ColumnSettingView;
import com.cditer.free.param.logical.mapper.IColumnSettingMapper;
import com.cditer.free.param.logical.service.IColumnSettingService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:45:22
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
