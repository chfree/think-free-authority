package com.cditer.free.debug.apis;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.jwt.core.CreateTokenFactory;
import com.cditer.free.jwt.core.JwtHelper;
import com.cditer.free.login.entity.apimodel.login.LoginLoadDataResp;
import com.cditer.free.login.entity.apimodel.login.LoginReq;
import com.cditer.free.login.utils.LoginUtil;
import com.cditer.free.security.annotation.TokenPassport;
import com.cditer.free.security.baseapi.TokenApi;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/loginweb/login/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginApi extends TokenApi {

    @Autowired
    private CreateTokenFactory createTokenFactory;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    @TokenPassport
    @Transactional
    public BaseResponse login(@Valid LoginReq loginReq){
        BaseResponse response = new BaseResponse();

        User user = new User();
        user.setAccount(loginReq.getUsername());
        user.setName("DEBUG");
        user.setId("debugId");

        loginSuccess(response, user);

        return response;
    }

    private void loginSuccess(BaseResponse response, User user) {
        LoginModel loginModel = LoginUtil.user2LoginModel(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("name", loginModel.getName());

        String token = createTokenFactory.newTokenCreate().createToken(loginModel.getId(),claims);
        loginModel.setToken(token);

        response.put("result",true);
        response.put("token",token);
    }



    @PostMapping("loginfo")
    public BaseResponse loginfo(){
        BaseResponse resp = new BaseResponse();

        resp.put("loginfo", new LoginModel());
        return resp;
    }

    @PostMapping("logout")
    public BaseResponse logout(){
        BaseResponse resp = new BaseResponse();

        resp.put("result", true);
        return resp;
    }


    @PostMapping("loginLoadData")
    public BaseResponse loginLoadData(){
        LoginLoadDataResp resp = new LoginLoadDataResp();

        return resp;
    }
}
