package com.cditer.free.behavior.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.cditer.free.behavior.dao.IDataEditLogDao;
import com.cditer.free.behavior.entity.base.IBehaviorModel;
import com.cditer.free.behavior.entity.base.IBehaviorQueryDb;
import com.cditer.free.behavior.entity.model.DataEditDtl;
import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.behavior.service.IDataEditLogService;
import com.cditer.free.core.message.data.ModelBase;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public int queryCountBySearch(DataEditLogSearch search) {
        return dataEditLogDao.queryCountBySearch(search);
    }

    @Override
    public List<DataEditLog> queryListBySearch(DataEditLogSearch search, PagerModel pagerModel) {
        return dataEditLogDao.queryListBySearch(search, pagerModel);
    }

    @Override
    public void saveListEditLog(List<? extends IBehaviorModel> list, LoginModel loginModel) {
        if (CollectionUtils.isEmpty(list) || loginModel == null) {
            return;
        }
        List<DataEditLogView> dataEditLogs = getDataEditLogs(list, loginModel);

        insertListEx(dataEditLogs);
    }

    private List<DataEditLogView> getDataEditLogs(List<? extends IBehaviorModel> list, LoginModel loginModel) {
        List<DataEditLogView> dataEditLogs = list.stream().map(item -> {
            DataEditLogView dataEditLog = new DataEditLogView();
            dataEditLog.setId(PkIdUtils.getId());

            dataEditLog.setBsnId(item.getBsnId());
            dataEditLog.setOperType(item.getOperType());
            dataEditLog.setBsnType(item.getBsnType());

            dataEditLog.setUserName(loginModel.getName());
            dataEditLog.setUserId(loginModel.getId());

            dataEditLog.setRecordDt(DateUtil.date());

            dataEditLog.setBehaviorModel(item);

            return dataEditLog;
        }).collect(Collectors.toList());

        return dataEditLogs;
    }

    @Override
    public void saveListEditLog(List<? extends IBehaviorModel> list, IBehaviorQueryDb behaviorQueryDb, LoginModel loginModel) {
        if (CollectionUtils.isEmpty(list) || loginModel == null || behaviorQueryDb == null) {
            return;
        }
        List<DataEditLogView> dataEditLogs = getDataEditLogs(list, loginModel);

        IBehaviorModel behaviorModel = list.get(0);
        List<DataEditDtl> dataEditDtlTemplate = buildDtlByModel(behaviorModel.getClass());

        for (DataEditLog dataEditLog : dataEditLogs) {
            ModelBase modelBase = behaviorQueryDb.queryModel(dataEditLog.getBsnId());
            List<DataEditDtl> dataEditDtls = new ArrayList<>();
            BeanUtil.copyProperties(dataEditDtlTemplate, dataEditDtls);

            for (DataEditDtl dataEditDtl : dataEditDtls) {
                Object oldFieldValue = BeanUtil.getFieldValue(modelBase, dataEditDtl.getProName());
                if(oldFieldValue!=null){
                    dataEditDtl.setOldVal(oldFieldValue.toString());
                }

                Object newFieldValue = BeanUtil.getFieldValue(modelBase, dataEditDtl.getProName());
                if(newFieldValue!=null){
                    dataEditDtl.setNewVal(newFieldValue.toString());
                }

            }

        }

        insertListEx(dataEditLogs);
    }

    private ArrayList<DataEditDtl> buildDtlByModel(Class<?> clazz){
        return null;
    }

}
