package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.login.LoginReq;
import com.tennetcn.free.authority.enums.LoginAuthStatus;
import com.tennetcn.free.authority.enums.LoginAuthType;
import com.tennetcn.free.authority.model.LoginAuth;
import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.authority.service.ILoginAuthService;
import com.tennetcn.free.authority.service.ILoginUserService;
import com.tennetcn.free.authority.utils.LoginUtil;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.annotation.ApiAuthPassport;
import com.tennetcn.free.security.core.JwtHelper;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/login/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="登陆模块",value ="登陆相关的操作" )
public class LoginApi extends AuthorityApi {

    @Autowired
    private ILoginUserService userService;

    @Autowired
    private ILoginAuthService loginAuthService;

    @Autowired
    JwtHelper jwtHelper;

    @ApiAuthPassport
    @ApiOperation(value = "登陆")
    @PostMapping("login")
    @Transactional
    public BaseResponse login(@Valid LoginReq loginReq){
        BaseResponse response = new BaseResponse();

        LoginUser user = userService.queryModelByLogin(loginReq.getUsername(),loginReq.getPassword());
        if(user==null){
            response.setMessage("用户名或密码不正确");
            response.setStatus(WebResponseStatus.DATA_ERROR);
            return response;
        }
        LoginModel loginModel = LoginUtil.user2LoginModel(user);

        Map<String,Object> claims = new HashMap<>();
        claims.put("account",loginModel.getAccount());
        claims.put("name",loginModel.getName());

        String token = jwtHelper.createJwt(user.getId(),claims);
        loginModel.setToken(token);

        addLoginAuth(loginModel,token);

        cached.put(token,loginModel);
        response.put("result",true);
        response.put("token",token);

        return response;
    }

    private void addLoginAuth(LoginModel loginModel,String token){
        LoginAuth loginAuth = new LoginAuth();

        Claims claims = jwtHelper.parseJWT(token);

        loginAuth.setId(IdUtil.randomUUID());
        loginAuth.setExpTm(claims.getExpiration());
        loginAuth.setToken(token);
        loginAuth.setUserId(loginModel.getId());
        loginAuth.setType(LoginAuthType.LOGIN.getKey());
        loginAuth.setStatus(LoginAuthStatus.VALID.getKey());
        loginAuth.setAuthTm(DateUtil.date());

        loginAuthService.saveLoginAuth(loginAuth);
    }

    @PostMapping("loginfo")
    public BaseResponse loginfo(){
        BaseResponse resp = new BaseResponse();

        LoginModel loginModel = getCurrentLogin();

        resp.put("loginfo", loginModel);
        return resp;
    }

    @PostMapping("logout")
    public BaseResponse logout(){
        BaseResponse resp = new BaseResponse();

        String token = getCurrentLogin().getToken();
        cached.remove(token);

        loginAuthService.updateStatusByToken(token,LoginAuthStatus.INVALID.getKey());

        resp.put("result", true);
        return resp;
    }
}
