package com.cditer.free.param.logical.service.impl;

import com.cditer.free.param.data.entity.model.TableSetting;
import com.cditer.free.param.data.entity.viewmodel.TableSettingSearch;
import com.cditer.free.param.data.entity.viewmodel.TableSettingView;
import com.cditer.free.param.logical.mapper.ITableSettingMapper;
import com.cditer.free.param.logical.service.ITableSettingService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;
import java.util.List;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:35:56
 * @comment     表信息配置
 */

@Component
public class TableSettingServiceImpl extends SuperService<TableSetting> implements ITableSettingService {
    @Autowired
    ITableSettingMapper tableSettingMapper;

    @Override
    public int queryCountBySearch(TableSettingSearch search) {
        return tableSettingMapper.queryCountBySearch(search);
    }

    @Override
    public List<TableSettingView> queryListViewBySearch(TableSettingSearch search, PagerModel pagerModel) {
        return tableSettingMapper.queryListViewBySearch(search,pagerModel);
    }

    @Override
    public TableSettingView queryModelViewBySearch(TableSettingSearch search) {
        return tableSettingMapper.queryModelViewBySearch(search);
    }

    @Override
    public TableSettingView queryModelViewById(String id) {
        TableSettingSearch search = new TableSettingSearch();
        search.setId(id);
        return tableSettingMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveTableSetting(TableSetting tableSetting) {
        tableSetting.autoPkIdAndStatus();

        return applyChange(tableSetting);
    }

}
