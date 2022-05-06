package com.cditer.free.security.intceptor.valid;

import com.cditer.free.jwt.core.JwtHelper;
import com.cditer.free.security.baseapi.TokenApi;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.security.handle.helper.TokenCheckInceptorHelper;
import com.cditer.free.security.handle.helper.TokenLoginModelInceptorHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    JwtHelper jwtHelper;

    public boolean checkAuthorizeJwt(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String token=request.getHeader("Authorization");
        Claims claims = jwtHelper.parseJWT(token);;

        // 检测token
        boolean checkTokenSuccess = checkToken(claims);
        if(!checkTokenSuccess){
            return false;
        }

        LoginModel loginModel = jwtHelper.token2LoginModel(token);
        TokenCheckInceptorHelper.checkToken(loginModel, token);

        request.setAttribute(TokenApi.LOGIN_KEY, loginModel);
        TokenLoginModelInceptorHelper.convertLoginModel(request, loginModel);

        return true;
    }

    // 数据token过期
    private boolean checkToken(Claims claims){
        if(claims == null){
            return false;
        }

        // token过期直接返回false
        if(jwtHelper.isTokenExpired(claims.getExpiration())){
            return false;
        }
        return true;
    }
}
