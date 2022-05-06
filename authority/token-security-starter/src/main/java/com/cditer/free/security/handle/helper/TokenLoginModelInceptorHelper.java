package com.cditer.free.security.handle.helper;

import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.security.handle.ITokenLoginModelInceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenLoginModelInceptorHelper {
    public static void convertLoginModel(HttpServletRequest request, LoginModel loginModel){
        Map<String, ITokenLoginModelInceptor> tokenLoginModelInceptorMap = SpringContextUtils.getCurrentContext().getBeansOfType(ITokenLoginModelInceptor.class);
        if(tokenLoginModelInceptorMap==null||tokenLoginModelInceptorMap.isEmpty()){
            return;
        }
        List<ITokenLoginModelInceptor> tokenLoginModelList = tokenLoginModelInceptorMap.values().stream().sorted(Comparator.comparing(ITokenLoginModelInceptor::getOrder)).collect(Collectors.toList());
        tokenLoginModelList.forEach(tokenLoginModel -> {
            tokenLoginModel.requestSetLoginModel(request, loginModel);
        });
    }
}
