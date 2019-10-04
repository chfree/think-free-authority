package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.login.LoginLoadDataResp;
import com.tennetcn.free.authority.apimodel.login.LoginReq;
import com.tennetcn.free.authority.handle.ILoginedInceptor;
import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.authority.service.ILoginUserService;
import com.tennetcn.free.authority.utils.LoginUtil;
import com.tennetcn.free.core.utils.CommonApplicationContextUtil;
import com.tennetcn.free.security.annotation.ApiAuthPassport;
import com.tennetcn.free.security.core.JwtHelper;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiAuthPassport
    @ApiOperation(value = "登陆")
    @PostMapping("login")
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

        String token = JwtHelper.instance().createJwt(user.getId(),claims);
        loginModel.setToken(token);

        cached.put(token,loginModel);
        response.put("result",true);
        response.put("token",token);

        return response;
    }

    @GetMapping("logout")
    public BaseResponse logout(){
        cached.remove(AuthorityApi.LOGIN_KEY);
        return new BaseResponse();
    }

    @PostMapping("loginLoadData")
    public BaseResponse loginLoadData(){
        LoginLoadDataResp resp = new LoginLoadDataResp();

        LoginModel loginModel = getCurrentLogin();

        ILoginedInceptor loginedIntceptor = CommonApplicationContextUtil.getCurrentContext().getBean(ILoginedInceptor.class);
        if(loginedIntceptor!=null){
            loginedIntceptor.additionLoginModel(loginModel,resp);
        }

        // 重新放入缓存
        cached.put(loginModel.getToken(),loginModel);
        resp.setLoginInfo(loginModel);

        return resp;
    }
}
