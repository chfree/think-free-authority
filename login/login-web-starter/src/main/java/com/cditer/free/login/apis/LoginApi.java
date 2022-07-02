package com.cditer.free.login.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.configuration.LoginConfig;
import com.cditer.free.authority.data.entity.model.Button;
import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.authority.data.entity.model.LoginAuth;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.MenuRoute;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.authority.data.enums.LoginAuthStatus;
import com.cditer.free.authority.data.enums.LoginAuthType;
import com.cditer.free.authority.data.enums.LoginStatus;
import com.cditer.free.authority.logical.service.IButtonService;
import com.cditer.free.authority.logical.service.IGroupService;
import com.cditer.free.authority.logical.service.ILoginAuthService;
import com.cditer.free.authority.logical.service.IMenuService;
import com.cditer.free.authority.logical.service.IRoleService;
import com.cditer.free.authority.logical.service.IUserService;
import com.cditer.free.core.cache.ICached;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.coreweb.message.WebResponseStatus;
import com.cditer.free.jwt.core.CreateTokenFactory;
import com.cditer.free.jwt.core.JwtHelper;
import com.cditer.free.login.entity.apimodel.login.LoginLoadDataResp;
import com.cditer.free.login.entity.apimodel.login.LoginReq;
import com.cditer.free.login.entity.apimodel.login.RegisterReq;
import com.cditer.free.login.handle.ILoginAllowIntceptor;
import com.cditer.free.login.handle.ILoginLoadDataIntceptor;
import com.cditer.free.login.handle.IRegisterLoginUserIntceptor;
import com.cditer.free.login.handle.helper.LoginedIntceptorHelper;
import com.cditer.free.login.utils.LoginUtil;
import com.cditer.free.security.annotation.TokenPassport;
import com.cditer.free.security.baseapi.TokenApi;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:05
 * @comment
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/loginweb/login/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginApi extends TokenApi {

    //数据异常
    private static final int LOGIN_ERROR = 1001;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginAuthService loginAuthService;

    @Autowired
    private CreateTokenFactory createTokenFactory;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private LoginConfig loginConfig;

    @Autowired
    private ICached cached;

    @PostMapping("login")
    @TokenPassport
    @Transactional
    public BaseResponse login(@Valid LoginReq loginReq) {
        BaseResponse response = new BaseResponse();

        if (loginConfig.isEncrypt()) {
            loginReq.resolveUserName(loginConfig.getLoginRsaPriKey());
        }

        User user = userService.queryModelByLogin(loginReq.getAccount(), loginReq.getPassword());
        if (user == null) {
            response.setMessage("用户名或密码不正确");
            response.setStatus(LOGIN_ERROR);
            return response;
        }

        // 用户名密码正确了，在检查状态是否正常,不是正常则不允许登陆
        if (!LoginStatus.NORMAL.getValue().equals(user.getStatus())) {
            response.setMessage("您的用户状态处于【" + LoginStatus.convert2Text(user.getStatus()) + "】，暂时无法登陆，请联系管理员");
            response.setStatus(LOGIN_ERROR);
            return response;
        }

        loginSuccess(response, user);

        return response;
    }

    private void loginSuccess(BaseResponse response, User user) {
        LoginModel loginModel = LoginUtil.user2LoginModel(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("name", loginModel.getName());

        List<Role> roles = roleService.queryListRoleByUserId(user.getId());

        if(!CollectionUtils.isEmpty(roles)){
            claims.put("roleId", roles.get(0).getId());
        }


        String token = createTokenFactory.newTokenCreate().createToken(loginModel.getId(), claims);
        loginModel.setToken(token);

        // 执行Logined切入点
        if (!isAllowLogin(loginModel, user)) {
            response.setMessage("该用户暂时无法登陆，请联系管理员");
            response.setStatus(LOGIN_ERROR);
            return;
        }

        LoginedIntceptorHelper.loginedCallback(loginModel);

        addLoginAuth(loginModel, token);


        cached.put(token, loginModel);
        response.put("result", true);
        response.put("token", token);
    }

    /**
     * 处理登陆授权信息
     *
     * @param loginModel loginModel实体
     * @param token      token信息
     */
    private void addLoginAuth(LoginModel loginModel, String token) {
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

    private boolean isAllowLogin(LoginModel loginModel, User loginUser) {
        ILoginAllowIntceptor loginAllowIntceptor = null;
        try {
            loginAllowIntceptor = SpringContextUtils.getCurrentContext().getBean(ILoginAllowIntceptor.class);
        } catch (NoSuchBeanDefinitionException ex) {
            log.info("No qualifying bean of type '{}' available", ILoginAllowIntceptor.class.getName());
            return true;
        }
        if (loginAllowIntceptor == null) {
            return true;
        }
        return loginAllowIntceptor.isAllowLogin(loginModel, loginUser);
    }

    @PostMapping("loginfo")
    public BaseResponse loginfo() {
        BaseResponse resp = new BaseResponse();

        LoginModel loginModel = getCurrentLogin();

        resp.put("loginfo", loginModel);
        return resp;
    }

    @PostMapping("logout")
    public BaseResponse logout() {
        BaseResponse resp = new BaseResponse();

        String token = getCurrentLogin().getToken();
        cached.remove(token);

        loginAuthService.updateStatusByToken(token, LoginAuthStatus.INVALID.getValue());

        resp.put("result", true);
        return resp;
    }

    @TokenPassport
    @PostMapping("getPubKey")
    public BaseResponse getPubKey() {
        BaseResponse resp = new BaseResponse();
        String pubKey = loginConfig.getLoginRsaPubKey();

        resp.put("pubKey", pubKey);
        return resp;
    }

    @PostMapping("loginLoadData")
    public BaseResponse loginLoadData() {
        LoginLoadDataResp resp = new LoginLoadDataResp();

        LoginModel loginModel = getCurrentLogin();

        additionLoginModel(loginModel, resp);

        // 在Intceptor中附加
        additionLoginModelIntceptor(loginModel, resp);

        // 重新放入缓存
        cached.put(loginModel.getToken(), loginModel);
        resp.setLoginInfo(loginModel);

        return resp;
    }

    private void additionLoginModelIntceptor(LoginModel loginModel, BaseResponse response) {
        Map<String, ILoginLoadDataIntceptor> loginLoadDataMap = SpringContextUtils.getCurrentContext().getBeansOfType(ILoginLoadDataIntceptor.class);
        if (loginLoadDataMap == null || loginLoadDataMap.values() == null || loginLoadDataMap.values().isEmpty()) {
            return;
        }
        // 按order 排序一下
        List<ILoginLoadDataIntceptor> loginLoadList = loginLoadDataMap.values().stream().sorted(Comparator.comparing(ILoginLoadDataIntceptor::getOrder)).collect(Collectors.toList());

        for (ILoginLoadDataIntceptor loadDataIntceptor : loginLoadList) {
            loadDataIntceptor.additionLoginModel(loginModel, response);
        }
    }

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IButtonService buttonService;

    public void additionLoginModel(LoginModel loginModel, BaseResponse response) {
        List<Role> roles = roleService.queryListRoleByUserId(loginModel.getId());
        List<Group> groups = groupService.queryListByUserId(loginModel.getId());

        List<String> roleIds = null;
        List<String> groupIds = null;
        if (roles != null && roles.size() > 0) {
            roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        }
        if (groups != null && groups.size() > 0) {
            groupIds = groups.stream().map(group -> group.getId()).collect(Collectors.toList());
        }

        User user = userService.queryModel(loginModel.getId());
        response.put("currUser", user);

        response.put("roles", roles);
        response.put("groups", groups);

        List<MenuRoute> menuRouteList = menuService.queryMenuRouteFormatByRGIds(roleIds, groupIds);
        response.put("menuRoutes", menuRouteList);

        List<Button> buttons = buttonService.queryListByRGds(roleIds, groupIds);
        response.put("buttons", buttons);

    }

    @TokenPassport
    @PostMapping("register")
    public BaseResponse register(@Valid RegisterReq registerReq) {
        BaseResponse response = new BaseResponse();
        if (!registerReq.getPassword().equals(registerReq.getConfirmPassword())) {
            response.setStatus(WebResponseStatus.DATA_ERROR);
            response.setMessage("新密码和旧密码不一致");
            return response;
        }
        UserSearch search = new UserSearch();
        search.setAccount(registerReq.getAccount());
        if (userService.queryCountByLoginUserSearch(search) > 0) {
            response.setStatus(WebResponseStatus.DATA_ERROR);
            response.setMessage("已经存在相同账号");
            return response;
        }

        User loginUser = regiester2User(registerReq);

        if (isRegister(loginUser, registerReq)) {
            response.put("result", userService.applyChange(loginUser));
        } else {
            log.info("IRegisterLoginUserIntceptor impl exec register return false");
            response.put("result", false);
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

    private User regiester2User(RegisterReq req) {
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

    @TokenPassport
    @PostMapping("accountExist")
    public BaseResponse accountExist(@Valid @NotBlank(message = "账号不能为空") String account) {
        BaseResponse response = new BaseResponse();

        UserSearch search = new UserSearch();
        search.setAccount(account);
        if (userService.queryCountByLoginUserSearch(search) > 0) {
            response.put("result", true);
        } else {
            response.put("result", false);
        }
        return response;
    }
}
