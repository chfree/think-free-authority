package com.cditer.free.dbdevops.logical.service.impl;

import com.cditer.free.core.exception.BizException;
import com.cditer.free.core.message.web.ResponseStatus;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.dbdevops.data.model.TableSetting;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.ColumnSettingView;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListKeyValView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListQueryResp;
import com.cditer.free.dbdevops.logical.mapper.IColumnSettingMapper;
import com.cditer.free.dbdevops.logical.mapper.ITableSettingMapper;
import com.cditer.free.dbdevops.logical.service.ITableSettingService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.cditer.free.data.dao.base.impl.SuperService;
import com.cditer.free.core.message.data.PagerModel;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:35:56
 * @comment     表信息配置
 */

@Component
public class TableSettingServiceImpl extends SuperService<TableSetting> implements ITableSettingService {
    @Autowired
    private ITableSettingMapper tableSettingMapper;

    @Autowired
    private IColumnSettingMapper columnSettingMapper;

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

    @Override
    public DataListQueryResp queryDataList(DataListKeyValView search, PagerModel pager) {
        String dataTabId = search.getDataTabId();
        TableSetting dataTabSetting = getDataTabSetting(dataTabId);

        List<ColumnSettingView> colList = getDataColSettingViews(dataTabId);

        DataListQueryResp resp = new DataListQueryResp();
//        ISqlExpression sqlExpressionCount = buildSqlCount(dataTabSetting, colList, search);
//        int count = dataColSettingDao.queryCount(sqlExpressionCount);
//        resp.setTotalCount(count);
//
//        ISqlExpression sqlExpression = buildSql(dataTabSetting, colList, search);
//        List<Map<String, Object>> dataList = dataColSettingDao.queryListEx(sqlExpression, pagerModel);
//        resp.setDataList(dataList);

        return resp;
    }

    private TableSetting getDataTabSetting(String dataTabId) {
        TableSetting dataTabSetting = queryModel(dataTabId);
        if (dataTabSetting == null) {
            throw new BizException(ResponseStatus.SERVER_ERROR, "找不到表配置记录");
        }
        return dataTabSetting;
    }

    private List<ColumnSettingView> getDataColSettingViews(String dataTabId) {
        ColumnSettingSearch colSearch = new ColumnSettingSearch();
        colSearch.setTableSettingId(dataTabId);
        List<ColumnSettingView> colList = columnSettingMapper.queryListViewBySearch(colSearch, PagerModel.asOf(1, 300));
        if (CollectionUtils.isEmpty(colList)) {
            throw new BizException(ResponseStatus.SERVER_ERROR, "找不到列配置记录");
        }
        return colList;
    }

}
