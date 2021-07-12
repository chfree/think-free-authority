package com.tennetcn.free.security.intceptor.valid;

import com.tennetcn.free.core.cache.ICached;
import com.tennetcn.free.core.util.SpringContextUtils;
import com.tennetcn.free.security.core.CreateTokenFactory;
import com.tennetcn.free.security.core.ITokenCreate;
import com.tennetcn.free.security.core.JwtHelper;
import com.tennetcn.free.security.handle.ILoginModelIntceptor;
import com.tennetcn.free.security.handle.ITokenCheckIntceptor;
import com.tennetcn.free.security.handle.ITokenUpdateIntceptor;
import com.tennetcn.free.security.handle.helper.LoginedIntceptorHelper;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-12-24 08:11
 * @comment
 */

@Slf4j
@Component
public class TokenHelper {

    @Autowired
    private ICached cached;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    CreateTokenFactory createTokenFactory;

    public boolean checkAuthorizeJwt(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String token=request.getHeader("Authorization");
        String refreshToken = request.getHeader("refreshToken");
        Claims claims = jwtHelper.parseJWT(token);;

        // 检测token
        boolean checkTokenSuccess = checkToken(token, refreshToken, claims,response);
        if(!checkTokenSuccess){
            return false;
        }

        // 处理loginModel
        LoginModel loginModel = resolveLoginModel(token,claims);
        if(loginModel==null){
            return false;
        }

        request.setAttribute(AuthorityApi.LOGIN_KEY, loginModel);
        return true;
    }

    private LoginModel resolveLoginModel(String token,Claims claims){
        LoginModel loginModel=(LoginModel)cached.get(token);
        if(loginModel!=null) {
            return loginModel;
        }

        log.info("取cache为空");
        // 如果cache为空，则由loginModelIntceptor进行注册一次
        ILoginModelIntceptor loginModelIntceptor = SpringContextUtils.getCurrentContext().getBean(ILoginModelIntceptor.class);
        if(loginModelIntceptor==null){
            return null;
        }

        /**
         * 重新注册完loginModel后，重新调起一下logined方法，将其他的附加也拉进来
         */
        loginModel = loginModelIntceptor.registerLoginModel(token,claims);
        if(loginModel==null){
            return null;
        }
        if(StringUtils.isEmpty(loginModel.getToken())){
            loginModel.setToken(token);
        }

        LoginedIntceptorHelper.loginedCallback(loginModel);

        // 在存储一次token
        cached.put(token,loginModel);


        // 在Jwt验证通过的情况下
        // 在进行自定义的处理方案
        boolean customTokenCheck = customTokenCheck(loginModel);
        if(!customTokenCheck){
            cached.remove(token);
            return null;
        }

        return loginModel;
    }

    // 数据token和刷新token无法精确预估固定的退出时间，只能是介于<刷新token时间-数据token时间>和<刷新token时间>之间的一个时间值
    private boolean checkToken(String token,String refreshToken,Claims claims, HttpServletResponse response){
        if(claims == null){
            cached.remove(token);
            return false;
        }

        if(!jwtHelper.isTokenExpired(claims.getExpiration())){
            return true;
        }

        // 如果token过期，则检测刷新token
        Claims refreshClaims = jwtHelper.parseJWT(refreshToken);
        // 刷新token过期，则直接返回
        if(jwtHelper.isTokenExpired(refreshClaims.getExpiration())){
            return false;
        }

        rebuildToken(refreshClaims,response);

        return true;
    }

    private void rebuildToken(Claims refreshClaims,HttpServletResponse response){
        // 重新分配数据token和刷新token
        ITokenCreate tokenCreate = createTokenFactory.newTokenCreate();

        String userId = refreshClaims.getId();
        String account = (String) refreshClaims.get(CreateTokenFactory.ACCOUNT);
        String name = (String) refreshClaims.get(CreateTokenFactory.NAME);

        String newToken = tokenCreate.createToken(userId, account, name);
        String newRefreshToken = tokenCreate.createRefreshToken(userId, account, name);

        response.setHeader("token", newToken);
        response.setHeader("refreshToken", newRefreshToken);
        response.setHeader("hasNewToken","true");
        updateToken(userId,newToken,newRefreshToken);
    }

    private boolean customTokenCheck(LoginModel loginModel){
        ITokenCheckIntceptor tokenCheckIntceptor = SpringContextUtils.getCurrentContext().getBean(ITokenCheckIntceptor.class);
        if(tokenCheckIntceptor==null){
            return true;
        }
        return tokenCheckIntceptor.checkToken(loginModel);
    }

    private void updateToken(String userId,String token,String refreshToken){
        ITokenUpdateIntceptor tokenUpdateIntceptor = SpringContextUtils.getCurrentContext().getBean(ITokenUpdateIntceptor.class);
        if(tokenUpdateIntceptor==null){
            tokenUpdateIntceptor.tokenUpdate(userId,token,refreshToken);
        }
    }
}
