package com.cditer.free.behavior.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.cditer.free.behavior.entity.model.WebVisitLog;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogSearch;
import com.cditer.free.behavior.entity.viewmodel.WebVisitLogView;
import com.cditer.free.behavior.mapper.IWebVisitLogMapper;
import com.cditer.free.behavior.service.IWebVisitLogService;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-14 12:17:28
 * @comment     操作日志表
 */

@Component
public class WebVisitLogServiceImpl extends SuperService<WebVisitLog> implements IWebVisitLogService {
    @Autowired
    private IWebVisitLogMapper webVisitLogMapper;

    @Override
    public int queryCountBySearch(WebVisitLogSearch search) {
        return webVisitLogMapper.queryCountBySearch(search);
    }

    @Override
    public List<WebVisitLogView> queryListViewBySearch(WebVisitLogSearch search, PagerModel pagerModel) {
        return webVisitLogMapper.queryListViewBySearch(search, pagerModel);
    }

    @Override
    public WebVisitLogView queryModelViewBySearch(WebVisitLogSearch search) {
        return webVisitLogMapper.queryModelViewBySearch(search);
    }

    @Override
    public WebVisitLogView queryModelViewById(String id) {
        WebVisitLogSearch search = new WebVisitLogSearch();
        search.setId(id);

        return webVisitLogMapper.queryModelViewBySearch(search);
    }

    @Override
    public boolean saveWebVisitLog(WebVisitLog webVisitLog) {
        webVisitLog.autoPkIdAndStatus();

        return applyChange(webVisitLog);
    }

    @Override
    public void batchSaveOperLog(List<WebVisitLog> operLogs) {
        if(CollectionUtils.isEmpty(operLogs)){
            return;
        }
        insertListEx(operLogs, 32);
    }

    @Override
    public void batchSaveOperLog(List<WebVisitLog> operLogs, LoginModel loginModel) {
        if(CollectionUtils.isEmpty(operLogs)){
           return;
        }
        for (WebVisitLog webVisitLog : operLogs) {
            webVisitLog.setId(PkIdUtils.getId());
            webVisitLog.setUserId(loginModel.getId());
            webVisitLog.setRoleId(loginModel.getRoleId());

            if(webVisitLog.getStartDt()!=null&&webVisitLog.getEndDt()!=null){
                long between = DateUtil.between(webVisitLog.getStartDt(), webVisitLog.getEndDt(), DateUnit.SECOND);
                webVisitLog.setDuration(between);
            }
        }

        batchSaveOperLog(operLogs);
    }

    @Override
    public int queryVisitCountBySearch(WebVisitLogSearch search) {
        return webVisitLogMapper.queryVisitCountBySearch(search);
    }
}
