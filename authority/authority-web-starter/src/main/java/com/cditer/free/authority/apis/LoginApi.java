package com.cditer.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.configuration.LoginConfig;
import com.cditer.free.authority.data.entity.apimodel.login.CheckUserLoginResp;
import com.cditer.free.authority.data.entity.apimodel.login.LoginReq;
import com.cditer.free.authority.data.entity.apimodel.login.RegisterReq;
import com.cditer.free.authority.data.entity.model.LoginAuth;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.LoginUserSearch;
import com.cditer.free.authority.data.enums.LoginAuthStatus;
import com.cditer.free.authority.data.enums.LoginAuthType;
import com.cditer.free.authority.data.enums.LoginStatus;
import com.cditer.free.authority.handle.ILoginAllowIntceptor;
import com.cditer.free.authority.handle.IRegisterLoginUserIntceptor;
import com.cditer.free.authority.logical.service.ILoginAuthService;
import com.cditer.free.authority.logical.service.ILoginUserService;
import com.cditer.free.authority.utils.LoginUtil;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.security.annotation.ApiAuthPassport;
import com.cditer.free.security.core.CreateTokenFactory;
import com.cditer.free.security.core.JwtHelper;
import com.cditer.free.security.handle.ILoginedIntceptor;
import com.cditer.free.security.handle.helper.LoginedIntceptorHelper;
import com.cditer.free.security.message.LoginModel;
import com.cditer.free.security.webapi.AuthorityApi;
import com.cditer.free.web.message.WebResponseStatus;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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

    @Autowired
    LoginConfig loginConfig;

    @ApiAuthPassport
    @ApiOperation(value = "登陆")
    @PostMapping("login")
    @Transactional
    public BaseResponse login(@Valid LoginReq loginReq){
        BaseResponse response = new BaseResponse();

        if(loginConfig.isEncrypt()){
            loginReq.resolveUserName(loginConfig.getLoginRsaPriKey());
        }

        User user = userService.queryModelByLogin(loginReq.getUsername(),loginReq.getPassword());
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

    private void loginSuccess(BaseResponse response, User user) {
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

    private boolean isAllowLogin(LoginModel loginModel,User loginUser){
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

        User loginUser = regiester2User(registerReq);

        if(isRegister(loginUser,registerReq)){
            response.put("result",userService.applyChange(loginUser));
        }else{
            log.info("IRegisterLoginUserIntceptor impl exec register return false");
            response.put("result",false);
        }
        return response;
    }

    private boolean isRegister(User user, RegisterReq req) {
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
        return registerLoginUserIntceptor.register(user, req);

    }

    private User regiester2User(RegisterReq req){
        User user = new User();
        user.setAccount(req.getAccount());
        user.setPassword(userService.passwordFormat(req.getPassword()));
        user.setCreateDate(DateUtil.date());
        user.setEmail(req.getAccount());
        user.setId(IdUtil.randomUUID());
        user.setName(req.getName());
        user.setModelStatus(ModelStatus.add);

        // 注册用户统一设置成待激活
        user.setStatus(LoginStatus.UNACTIVE.getValue());

        return user;
    }

    @ApiAuthPassport
    @ApiOperation(value = "获取公钥信息")
    @PostMapping("getPubKey")
    public BaseResponse getPubKey(){
        BaseResponse resp = new BaseResponse();
        String pubKey = loginConfig.getLoginRsaPubKey();

        resp.put("pubKey", pubKey);
        return resp;
    }


    /**
     * 根据用户id判断是否能正常登陆
     */
    @ApiOperation(value = "根据用户id判断是否能正常登陆")
    @PostMapping("checkUserLogin")
    public CheckUserLoginResp checkUserLogin(String userId){
        CheckUserLoginResp resp = new CheckUserLoginResp();

        if(StringUtils.hasText(userId)){
            resp.setAllowLogin(false);
            resp.setMessage("用户id为空");
            return resp;
        }

        User user = userService.queryCountByUserId(userId);
        if(user==null){
            resp.setAllowLogin(false);
            resp.setMessage("用户不存在");

            return resp;
        }

        // 用户名密码正确了，在检查状态是否正常,不是正常则不允许登陆
        if(!LoginStatus.NORMAL.getValue().equals(user.getStatus())) {
            resp.setAllowLogin(false);
            resp.setMessage("您的用户状态处于【" + LoginStatus.convert2Text(user.getStatus()) + "】，暂时无法登陆，请联系管理员");

            return resp;
        }

        resp.setAllowLogin(true);
        return resp;
    }
}
