package com.cditer.free.security.handle.helper;

import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.security.handle.ITokenCheckInceptor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TokenCheckInceptorHelper {
    public static void checkToken(LoginModel loginModel,String token){
        Map<String, ITokenCheckInceptor> tokenCheckInceptorMap = SpringContextUtils.getCurrentContext().getBeansOfType(ITokenCheckInceptor.class);
        if(tokenCheckInceptorMap==null||tokenCheckInceptorMap.isEmpty()){
            return;
        }
        List<ITokenCheckInceptor> tokenCheckList = tokenCheckInceptorMap.values().stream().sorted(Comparator.comparing(ITokenCheckInceptor::getOrder)).collect(Collectors.toList());
        tokenCheckList.forEach(tokenCheck -> {
            tokenCheck.checkToken(loginModel, token);
        });
    }
}
