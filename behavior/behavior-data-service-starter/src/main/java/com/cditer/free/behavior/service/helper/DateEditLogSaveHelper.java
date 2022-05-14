package com.cditer.free.behavior.service.helper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cditer.free.behavior.anno.BsnId;
import com.cditer.free.behavior.anno.DataEditDtlMark;
import com.cditer.free.behavior.anno.DataEditLogMark;
import com.cditer.free.behavior.entity.viewmodel.DataEditDtlView;
import com.cditer.free.behavior.entity.viewmodel.DataEditLogView;
import com.cditer.free.core.message.data.IDbModel;
import com.cditer.free.core.message.data.ModelBase;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.PkIdUtils;
import com.cditer.free.core.util.ReflectUtils;
import com.cditer.free.data.dao.base.ISqlExecutor;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DateEditLogSaveHelper {

    @Autowired
    ISqlExecutor sqlExecutor;

    public List<DataEditLogView> getDataEditLogs(List<? extends IDbModel> list, LoginModel loginModel) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Class<? extends IDbModel> modelType = list.get(0).getClass();
        DataEditLogMark dataEditLogMark = modelType.getAnnotation(DataEditLogMark.class);
        if (dataEditLogMark == null) {
            return null;
        }

        List<Field> declaredFields = ReflectUtils.getAllFieldList(modelType, IDbModel.class);
        if (CollectionUtils.isEmpty(declaredFields)) {
            return null;
        }

        List<DataEditDtlView> dataEditDtlTemplate = buildDtlByModel(modelType);

        List<DataEditLogView> dataEditLogs = list.stream().map(item -> {
            DataEditLogView dataEditLog = new DataEditLogView();
            dataEditLog.setId(PkIdUtils.getId());

            dataEditLog.setUserName(loginModel.getName());
            dataEditLog.setUserId(loginModel.getId());
            dataEditLog.setBsnType(dataEditLogMark.bsnType());
            dataEditLog.setRecordDtl(dataEditLogMark.recordDtl());
            setBsnId(declaredFields, item, dataEditLog);
            dataEditLog.setOperType(getOperType(item));

            dataEditLog.setRecordDt(DateUtil.date());

            if (dataEditLogMark.recordDtl()) {
                buildDataEditDtl(dataEditLog, item, dataEditDtlTemplate);
            }

            return dataEditLog;
        }).filter(item -> item.isRecord()).collect(Collectors.toList());

        return dataEditLogs;
    }

    private String getOperType(IDbModel dbModel) {
        if (dbModel instanceof ModelBase) {
            return ((ModelBase) dbModel).getModelStatus().name();
        }
        return null;
    }

    private void setBsnId(List<Field> declaredFields, IDbModel dbModel, DataEditLogView dataEditLogView) {
        // 先获取@Id
        Optional<Field> first = declaredFields.stream().filter(item -> item.getAnnotation(Id.class) != null).findFirst();
        if (!first.isPresent()) {
            // 如果空，则获取@BsnId
            first = declaredFields.stream().filter(item -> item.getAnnotation(BsnId.class) != null).findFirst();
            if (!first.isPresent()) {
                return;
            }
        }
        Object fieldValue = BeanUtil.getFieldValue(dbModel, first.get().getName());
        if (fieldValue == null) {
            return;
        }
        Column annotation = first.get().getAnnotation(Column.class);
        if (annotation != null) {
            dataEditLogView.setBsnIdFieldName(annotation.name());
        } else {
            dataEditLogView.setBsnIdFieldName(first.get().getName());
        }
        dataEditLogView.setBsnId(fieldValue.toString());


    }

    private void buildDataEditDtl(DataEditLogView dataEditLog, IDbModel dbModel, List<DataEditDtlView> dataEditDtlTemplate) {

        IDbModel modelBase = queryModelBase(dataEditLog, dbModel);
        List<DataEditDtlView> dataEditDtls = new ArrayList<>();

        for (DataEditDtlView temp : dataEditDtlTemplate) {
            DataEditDtlView dataEditDtl = new DataEditDtlView();
            dataEditDtls.add(dataEditDtl);
            BeanUtil.copyProperties(temp, dataEditDtl);

            dataEditDtl.setId(PkIdUtils.getId());
            dataEditDtl.setEditId(dataEditLog.getId());

            Object oldFieldValue = BeanUtil.getFieldValue(modelBase, dataEditDtl.getProName());
            Object newFieldValue = BeanUtil.getFieldValue(dbModel, dataEditDtl.getProName());

            dataEditDtl.setEq(ObjectUtil.equal(oldFieldValue, newFieldValue));

            if (!dataEditDtl.isEq()) {
                if (oldFieldValue != null) {
                    dataEditDtl.setOldVal(oldFieldValue.toString());
                }
                if (newFieldValue != null) {
                    dataEditDtl.setNewVal(newFieldValue.toString());
                }
            }
        }
        List<DataEditDtlView> saveList = dataEditDtls.stream().filter(item -> !item.isEq()).collect(Collectors.toList());
        dataEditLog.setDataEditDtlViewList(saveList);
    }

    private IDbModel queryModelBase(DataEditLogView dataEditLog, IDbModel dbModel) {
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        Class<? extends IDbModel> clazzByAnno = getClassByAnno(dbModel.getClass(), Table.class);
        sqlExpression.selectAllFrom(clazzByAnno)
                .andEq(dataEditLog.getBsnIdFieldName(), dataEditLog.getBsnId());

        return sqlExecutor.selectOne(sqlExpression, clazzByAnno);
    }

    private Class<? extends IDbModel> getClassByAnno(Class<?> clazz, Class<? extends Annotation> anno) {
        if (clazz.getName().equals(Object.class.getName())) {
            return null;
        }
        if (clazz.getAnnotation(anno) != null) {
            return (Class<? extends ModelBase>) clazz;
        }
        return getClassByAnno(clazz.getSuperclass(), anno);
    }

    private List<DataEditDtlView> buildDtlByModel(Class<?> clazz) {
        List<Field> allFieldList = ReflectUtils.getAllFieldList(clazz, ModelBase.class);

        if (CollectionUtils.isEmpty(allFieldList)) {
            return null;
        }
        return allFieldList.stream().map(item -> {
            DataEditDtlView bsnMnpltDtl = new DataEditDtlView();

            DataEditDtlMark annotation = item.getAnnotation(DataEditDtlMark.class);
            if (annotation == null) {
                return null;
            }
            bsnMnpltDtl.setProName(item.getName());
            bsnMnpltDtl.setLevel(annotation.level());

            return bsnMnpltDtl;
        }).filter(item -> item != null).collect(Collectors.toList());
    }
}
