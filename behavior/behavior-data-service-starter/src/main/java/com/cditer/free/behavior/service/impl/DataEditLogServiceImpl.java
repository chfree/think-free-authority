package com.cditer.free.behavior.service.impl;

import com.cditer.free.behavior.dao.IDataEditLogDao;
import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.behavior.service.IDataEditDtlService;
import com.cditer.free.behavior.service.IDataEditLogService;
import com.cditer.free.behavior.service.helper.DateEditLogSaveHelper;
import com.cditer.free.core.message.data.IDbModel;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author auto build code by think
 * @email chfree001@gmail.com
 * @createtime 2022-04-27 21:06:54
 * @comment 数据修改记录
 */

@Component
public class DataEditLogServiceImpl extends SuperService<DataEditLog> implements IDataEditLogService {
    @Autowired
    IDataEditLogDao dataEditLogDao;

    @Autowired
    IDataEditDtlService dataEditDtlService;

    @Autowired
    DateEditLogSaveHelper dateEditLogSaveHelper;

    @Override
    public int queryCountBySearch(DataEditLogSearch search) {
        return dataEditLogDao.queryCountBySearch(search);
    }

    @Override
    public List<DataEditLog> queryListBySearch(DataEditLogSearch search, PagerModel pagerModel) {
        return dataEditLogDao.queryListBySearch(search, pagerModel);
    }

    @Override
    public void saveListEditLog(List<? extends IDbModel> list, LoginModel loginModel) {
        if (CollectionUtils.isEmpty(list) || loginModel == null) {
            return;
        }
        List<DataEditLogView> dataEditLogs = dateEditLogSaveHelper.getDataEditLogs(list, loginModel);
        if(CollectionUtils.isEmpty(dataEditLogs)){
            return;
        }

        List<DataEditDtlView> dataEditDtlList = new ArrayList<>();
        for (DataEditLogView dataEditLog : dataEditLogs) {
            List<DataEditDtlView> dtlList = dataEditLog.getDataEditDtlViewList();
            if(CollectionUtils.isEmpty(dtlList)){
                continue;
            }
            dataEditDtlList.addAll(dtlList);
        }

        insertListEx(dataEditLogs);

        if(!CollectionUtils.isEmpty(dataEditDtlList)){
            dataEditDtlService.insertListEx(dataEditDtlList);
        }
    }
}
