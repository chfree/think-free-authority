package com.cditer.free.security.intceptor.valid;

import com.cditer.free.security.core.CreateTokenFactory;
import com.cditer.free.security.core.JwtHelper;
import com.cditer.free.security.handle.ITokenCheckIntceptor;
import com.cditer.free.core.cache.ICached;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.security.handle.ILoginModelIntceptor;
import com.cditer.free.security.handle.helper.LoginedIntceptorHelper;
import com.cditer.free.security.message.LoginModel;
import com.cditer.free.coreweb.security.AuthorityApi;
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
        Claims claims = jwtHelper.parseJWT(token);;

        // 检测token
        boolean checkTokenSuccess = checkToken(token, claims,response);
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

    // 数据token过期
    private boolean checkToken(String token,Claims claims, HttpServletResponse response){
        if(claims == null){
            cached.remove(token);
            return false;
        }

        // token过期直接返回false
        if(jwtHelper.isTokenExpired(claims.getExpiration())){
            return false;
        }
        return true;
    }


    private boolean customTokenCheck(LoginModel loginModel){
        ITokenCheckIntceptor tokenCheckIntceptor = SpringContextUtils.getCurrentContext().getBean(ITokenCheckIntceptor.class);
        if(tokenCheckIntceptor==null){
            return true;
        }
        return tokenCheckIntceptor.checkToken(loginModel);
    }
}
