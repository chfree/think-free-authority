package com.cditer.free.behavior.handle.helper;

import com.cditer.free.behavior.entity.model.WebVisitLimit;
import com.cditer.free.behavior.handle.ISurpassLimitInceptor;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.SpringContextUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurpassLimitInceptorHelper {

    public static void execSurpassLimitNotify(LoginModel loginModel, WebVisitLimit webVisitLimit, int visitSumCount) {
        Map<String, ISurpassLimitInceptor> inceptorMap = SpringContextUtils.getCurrentContext().getBeansOfType(ISurpassLimitInceptor.class);
        if (inceptorMap == null || inceptorMap.isEmpty()) {
            return;
        }
        List<ISurpassLimitInceptor> inceptorList = inceptorMap.values().stream().sorted(Comparator.comparing(ISurpassLimitInceptor::getOrder)).collect(Collectors.toList());
        inceptorList.forEach(loginedItem -> {
            loginedItem.surpassLimit(loginModel, webVisitLimit, visitSumCount);
        });
    }


}
