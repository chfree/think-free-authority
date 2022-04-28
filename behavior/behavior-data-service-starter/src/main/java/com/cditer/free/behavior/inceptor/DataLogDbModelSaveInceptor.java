package com.cditer.free.behavior.inceptor;

import com.cditer.free.behavior.anno.DataEditLogMark;
import com.cditer.free.behavior.service.IDataEditLogService;
import com.cditer.free.core.inceptor.ILoginModelQuery;
import com.cditer.free.core.message.data.IDbModel;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.data.dao.base.ISqlExecutor;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.inceptor.IDbModelSaveInceptor;
import com.cditer.free.data.utils.SqlExpressionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class DataLogDbModelSaveInceptor implements IDbModelSaveInceptor {

    @Autowired
    IDataEditLogService dataEditLogService;

    @Autowired
    @Qualifier("loginModelQueryTokenImpl")
    ILoginModelQuery loginModelQuery;

    @Autowired
    ISqlExecutor sqlExecutor;

    @Override
    public void saveAfter(List<? extends IDbModel> list) {
        if(CollectionUtils.isEmpty(list)){
            return;
        }
    }

    @Override
    public void saveBefore(List<? extends IDbModel> list) {
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        DataEditLogMark dataEditLogMark = list.get(0).getClass().getAnnotation(DataEditLogMark.class);
        if(dataEditLogMark==null){
            return;
        }

        LoginModel currentLogin = loginModelQuery.getCurrentLogin();
        dataEditLogService.saveListEditLog(list, currentLogin);

    }
}
