package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.entity.apimodel.login.LoginReq;
import com.tennetcn.free.authority.entity.apimodel.login.RegisterReq;
import com.tennetcn.free.authority.enums.LoginAuthStatus;
import com.tennetcn.free.authority.enums.LoginAuthType;
import com.tennetcn.free.authority.enums.LoginStatus;
import com.tennetcn.free.authority.handle.ILoginAllowIntceptor;
import com.tennetcn.free.authority.handle.IRegisterLoginUserIntceptor;
import com.tennetcn.free.security.core.CreateTokenFactory;
import com.tennetcn.free.security.handle.ILoginedIntceptor;
import com.tennetcn.free.authority.entity.model.LoginAuth;
import com.tennetcn.free.authority.entity.model.LoginUser;
import com.tennetcn.free.authority.logical.service.ILoginAuthService;
import com.tennetcn.free.authority.logical.service.ILoginUserService;
import com.tennetcn.free.authority.utils.LoginUtil;
import com.tennetcn.free.authority.entity.viewmodel.LoginUserSearch;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.core.util.SpringContextUtils;
import com.tennetcn.free.security.annotation.ApiAuthPassport;
import com.tennetcn.free.security.core.JwtHelper;
import com.tennetcn.free.security.handle.helper.LoginedIntceptorHelper;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authority/login/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="登陆模块",value ="登陆相关的操作" )
public class LoginApi extends AuthorityApi {

    @Autowired
    private ILoginUserService userService;

    @Autowired
    private ILoginAuthService loginAuthService;

    @Autowired
    CreateTokenFactory createTokenFactory;

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

        // 用户名密码正确了，在检查状态是否正常,不是正常则不允许登陆
        if(!LoginStatus.NORMAL.getValue().equals(user.getStatus())){
            response.setMessage("您的用户状态处于【"+LoginStatus.convert2Text(user.getStatus())+"】，暂时无法登陆，请联系管理员");
            response.setStatus(WebResponseStatus.DATA_ERROR);
            return response;
        }

        loginSuccess(response, user);

        return response;
    }

    private void loginSuccess(BaseResponse response, LoginUser user) {
        LoginModel loginModel = LoginUtil.user2LoginModel(user);

        String token = createTokenFactory.newTokenCreate().createToken(loginModel.getId(),loginModel.getAccount(),loginModel.getName());
        loginModel.setToken(token);

        // 执行Logined切入点
        if(!isAllowLogin(loginModel, user)){
            response.setMessage("该用户暂时无法登陆，请联系管理员");
            response.setStatus(WebResponseStatus.DATA_ERROR);
            return;
        }

        LoginedIntceptorHelper.loginedCallback(loginModel);

        addLoginAuth(loginModel,token);


        cached.put(token,loginModel);
        response.put("result",true);
        response.put("token",token);
    }

    /**
     * 处理登陆授权信息
     * @param loginModel loginModel实体
     * @param token token信息
     */
    private void addLoginAuth(LoginModel loginModel,String token){
        LoginAuth loginAuth = new LoginAuth();

        Claims claims = jwtHelper.parseJWT(token);

        loginAuth.setId(IdUtil.randomUUID());
        loginAuth.setExpTm(claims.getExpiration());
        loginAuth.setToken(token);
        loginAuth.setUserId(loginModel.getId());
        loginAuth.setType(LoginAuthType.LOGIN.getValue());
        loginAuth.setStatus(LoginAuthStatus.VALID.getValue());
        loginAuth.setAuthTm(DateUtil.date());

        loginAuthService.saveLoginAuth(loginAuth);
    }

    private boolean isAllowLogin(LoginModel loginModel,LoginUser loginUser){
        ILoginAllowIntceptor loginedIntceptor = null;
        try{
            loginedIntceptor = SpringContextUtils.getCurrentContext().getBean(ILoginAllowIntceptor.class);
        }catch (NoSuchBeanDefinitionException ex){
            log.info("No qualifying bean of type '{}' available", ILoginedIntceptor.class.getName());
            return true;
        }
        if(loginedIntceptor == null){
            return true;
        }
        return loginedIntceptor.isAllowLogin(loginModel, loginUser);
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

        loginAuthService.updateStatusByToken(token,LoginAuthStatus.INVALID.getValue());

        resp.put("result", true);
        return resp;
    }

    @ApiAuthPassport
    @ApiOperation(value = "检测用户是否已经被注册")
    @PostMapping("accountExist")
    public BaseResponse accountExist(@Valid @NotBlank(message = "账号不能为空") String account){
        BaseResponse response = new BaseResponse();

        LoginUserSearch search=new LoginUserSearch();
        search.setAccount(account);
        if(userService.queryCountByLoginUserSearch(search)>0){
            response.put("result", true);
        }else{
            response.put("result", false);
        }
        return response;
    }

    @ApiAuthPassport
    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    public BaseResponse register(@Valid RegisterReq registerReq){
        BaseResponse response = new BaseResponse();
        if(!registerReq.getPassword().equals(registerReq.getConfirmPassword())){
            response.setStatus(WebResponseStatus.DATA_ERROR);
            response.setMessage("新密码和旧密码不一致");
            return response;
        }
        LoginUserSearch search=new LoginUserSearch();
        search.setAccount(registerReq.getAccount());
        if(userService.queryCountByLoginUserSearch(search)>0){
            response.setStatus(WebResponseStatus.DATA_ERROR);
            response.setMessage("已经存在相同账号");
            return response;
        }

        LoginUser loginUser = regiester2User(registerReq);

        if(isRegister(loginUser,registerReq)){
            response.put("result",userService.applyChange(loginUser));
        }else{
            log.info("IRegisterLoginUserIntceptor impl exec register return false");
            response.put("result",false);
        }
        return response;
    }

    private boolean isRegister(LoginUser loginUser, RegisterReq req) {
        IRegisterLoginUserIntceptor registerLoginUserIntceptor = null;
        try {
            registerLoginUserIntceptor = SpringContextUtils.getCurrentContext().getBean(IRegisterLoginUserIntceptor.class);
        } catch (NoSuchBeanDefinitionException ex) {
            log.info("No qualifying bean of type '{}' available", IRegisterLoginUserIntceptor.class.getName());
            return true;
        }
        if (registerLoginUserIntceptor == null) {
            return true;
        }
        return registerLoginUserIntceptor.register(loginUser, req);

    }

    private LoginUser regiester2User(RegisterReq req){
        LoginUser loginUser = new LoginUser();
        loginUser.setAccount(req.getAccount());
        loginUser.setPassword(userService.passwordFormat(req.getPassword()));
        loginUser.setCreateDate(DateUtil.date());
        loginUser.setEmail(req.getAccount());
        loginUser.setId(IdUtil.randomUUID());
        loginUser.setName(req.getName());
        loginUser.setModelStatus(ModelStatus.add);

        // 注册用户统一设置成待激活
        loginUser.setStatus(LoginStatus.UNACTIVE.getValue());

        return loginUser;
    }
}
