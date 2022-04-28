package com.cditer.free.behavior.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cditer.free.behavior.anno.BsnId;
import com.cditer.free.behavior.anno.DataEditDtlMark;
import com.cditer.free.behavior.anno.DataEditLogMark;
import com.cditer.free.behavior.dao.IDataEditLogDao;
import com.cditer.free.behavior.entity.base.IBehaviorQueryDb;
import com.cditer.free.behavior.entity.model.DataEditLog;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogSearch;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.behavior.service.IDataEditDtlService;
import com.cditer.free.behavior.service.IDataEditLogService;
import com.cditer.free.core.message.data.IDbModel;
import com.cditer.free.core.message.data.ModelBase;
import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.util.ReflectUtils;
import com.cditer.free.data.dao.base.impl.SuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    IDataEditDtlService dataEditDtlService;

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
        List<DataEditLogView> dataEditLogs = getDataEditLogs(list, loginModel);

        insertListEx(dataEditLogs);
    }

    private List<DataEditLogView> getDataEditLogs(List<? extends IDbModel> list, LoginModel loginModel) {
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        DataEditLogMark dataEditLogMark = list.get(0).getClass().getAnnotation(DataEditLogMark.class);
        if(dataEditLogMark==null){
            return null;
        }

        List<DataEditLogView> dataEditLogs = list.stream().map(item -> {
            DataEditLogView dataEditLog = new DataEditLogView();
            dataEditLog.setId(PkIdUtils.getId());

            dataEditLog.setUserName(loginModel.getName());
            dataEditLog.setUserId(loginModel.getId());
            dataEditLog.setBsnType(dataEditLogMark.bsnType());
            dataEditLog.setRecordDtl(dataEditLogMark.recordDtl());
            dataEditLog.setBsnId(getBsnId(item));


            dataEditLog.setRecordDt(DateUtil.date());

            return dataEditLog;
        }).filter(item -> item.isRecord()).collect(Collectors.toList());

        return dataEditLogs;
    }

    private String getBsnId(IDbModel dbModel){
        List<Field> declaredFields = ReflectUtils.getAllFieldList(dbModel.getClass(), ModelBase.class);
        // 先获取@Id
        Optional<Field> first = declaredFields.stream().filter(item -> item.getAnnotation(Id.class) != null).findFirst();
        if(!first.isPresent()){
            // 如果空，则获取@BsnId
            first = declaredFields.stream().filter(item -> item.getAnnotation(BsnId.class) != null).findFirst();
            if(!first.isPresent()) {
                return null;
            }
        }
        Object fieldValue = BeanUtil.getFieldValue(dbModel, first.get().getName());
        if(fieldValue==null){
            return null;
        }
        return fieldValue.toString();
    }

    @Override
    public void saveListEditLog(List<? extends IDbModel> list, IBehaviorQueryDb behaviorQueryDb, LoginModel loginModel) {
        if (CollectionUtils.isEmpty(list) || loginModel == null || behaviorQueryDb == null) {
            return;
        }
        List<DataEditLogView> dataEditLogs = getDataEditLogs(list, loginModel);
        List<DataEditDtlView> dataEditDtlViews = new ArrayList<>();

        IDbModel dbModel = list.get(0);
        List<DataEditDtlView> dataEditDtlTemplate = buildDtlByModel(dbModel.getClass());

        for (DataEditLog dataEditLog : dataEditLogs) {
            ModelBase modelBase = behaviorQueryDb.queryModel(dataEditLog.getBsnId());
            List<DataEditDtlView> dataEditDtls = new ArrayList<>();
            BeanUtil.copyProperties(dataEditDtlTemplate, dataEditDtls);

            for (DataEditDtlView dataEditDtl : dataEditDtls) {
                dataEditDtl.setId(PkIdUtils.getId());
                dataEditDtl.setEditId(dataEditLog.getId());

                Object oldFieldValue = BeanUtil.getFieldValue(modelBase, dataEditDtl.getProName());
                Object newFieldValue = BeanUtil.getFieldValue(modelBase, dataEditDtl.getProName());

                dataEditDtl.setEq(ObjectUtil.equal(oldFieldValue, newFieldValue));

                if(!ObjectUtil.equal(oldFieldValue, newFieldValue)){
                    if(oldFieldValue!=null){
                        dataEditDtl.setOldVal(oldFieldValue.toString());
                    }
                    if(newFieldValue!=null){
                        dataEditDtl.setNewVal(newFieldValue.toString());
                    }
                }
            }

            dataEditDtlViews.addAll(dataEditDtls);
        }

        insertListEx(dataEditLogs);

        dataEditDtlService.insertListEx(dataEditDtlViews);
    }

    private List<DataEditDtlView> buildDtlByModel(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();

        if(fields==null&&fields.length<=0) {
            return null;
        }
        return Arrays.stream(fields).map(item -> {
            DataEditDtlView bsnMnpltDtl = new DataEditDtlView();

            DataEditDtlMark annotation = item.getAnnotation(DataEditDtlMark.class);
            if(annotation==null){
                return null;
            }
            bsnMnpltDtl.setProName(item.getName());
            bsnMnpltDtl.setProText(annotation.text());
            bsnMnpltDtl.setLevel(annotation.level());

            return bsnMnpltDtl;
        }).filter(item->item!=null).collect(Collectors.toList());
    }




}
