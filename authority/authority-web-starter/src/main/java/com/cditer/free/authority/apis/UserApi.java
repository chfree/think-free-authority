package com.cditer.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.data.entity.apimodel.login.LoginLoadDataResp;
import com.cditer.free.authority.data.entity.apimodel.login.RegisterReq;
import com.cditer.free.authority.data.entity.apimodel.user.*;
import com.cditer.free.authority.data.entity.model.Button;
import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.authority.data.entity.viewmodel.LoginUserSearch;
import com.cditer.free.authority.data.entity.viewmodel.MenuRoute;
import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.authority.data.entity.viewmodel.UserView;
import com.cditer.free.authority.exception.AuthorityBizException;
import com.cditer.free.authority.handle.ILoginLoadDataIntceptor;
import com.cditer.free.authority.handle.IRegisterLoginUserIntceptor;
import com.cditer.free.authority.logical.service.*;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.util.SpringContextUtils;
import com.cditer.free.coreweb.message.WebResponseStatus;
import com.cditer.free.coreweb.security.AuthorityApi;
import com.cditer.free.security.message.LoginModel;
import com.cditer.free.login.service.logical.enums.LoginStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:03
 * @comment
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authority/user/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="用户模块",value ="用户相关的操作" )
public class UserApi extends AuthorityApi {

    @Autowired
    IUserService userService;

    @Autowired
    IMenuService menuService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IButtonService buttonService;

    @Autowired
    IGroupService groupService;

    @ApiOperation(value = "获取用户列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid UserListReq listReq){
        UserListResp response = new UserListResp();
        response.setTotalCount(userService.queryCountBySearch(listReq.getSearch()));
        response.setUsers(userService.queryViewListBySearch(listReq.getSearch(),listReq.getPager()));

        return response;
    }

    @ApiOperation(value = "获取指定用户")
    @GetMapping("get")
    public UserGetResp get(@Valid @NotBlank(message = "用户id不能为空") String id){
        UserGetResp response=new UserGetResp();

        UserView user = userService.queryViewModelById(id);
        response.setUser(user);

        return response;
    }

    @ApiOperation(value = "搜索用户数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(UserSearch search){
        BaseResponse response=new BaseResponse();

        int count =  userService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定用户")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个用户")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveUserReq userReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(userReq.getId())){
            userReq.setId(IdUtil.randomUUID());
            userReq.setModelStatus(ModelStatus.add);
            userReq.setStatus(LoginStatus.NORMAL.getValue());
        }else{
            userReq.setModelStatus(ModelStatus.update);
        }

        boolean result = userService.saveUser(userReq);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "更改密码")
    @PostMapping("updatePwd")
    public BaseResponse updatePwd(@Valid UpdatePwd updatePwd){
        BaseResponse response = new BaseResponse();
        if(!updatePwd.getNewPwd().equals(updatePwd.getConfirmNewPwd())){
            response.setMessage("两次密码输入不一致");
            response.setStatus(WebResponseStatus.DATA_ERROR);

            return response;
        }
        User user = userService.queryModelByLogin(updatePwd.getAccount(),updatePwd.getOldPwd());
        if(user==null){
            response.setMessage("账号或密码输入不正确");
            response.setStatus(WebResponseStatus.DATA_ERROR);

            return response;
        }
        user.setPassword(userService.passwordFormat(updatePwd.getNewPwd()));

        response.put("result",userService.updateModel(user));

        return response;
    }

    @ApiOperation(value = "验证用户名密码")
    @PostMapping("checkAccountAndPwd")
    public BaseResponse checkAccountAndPwd(@Valid @NotBlank(message = "用户账号不能为空") String account,@Valid @NotBlank(message = "密码不能为空") String pwd){
        BaseResponse response = new BaseResponse();

        User user = userService.queryModelByLogin(account,pwd);
        response.put("result", user !=null);

        return response;
    }

    @ApiOperation(value = "强制更改密码")
    @PostMapping("forceUpdatePwd")
    public BaseResponse forceUpdatePwd(@Valid @NotBlank(message = "用户id不能为空") String id,@Valid @NotBlank(message = "密码不能为空") String pwd){
        BaseResponse response = new BaseResponse();
        User user = userService.queryModel(id);

        if(user==null){
            throw new AuthorityBizException("用户不存在，更改密码失败");
        }
        user.setPassword(userService.passwordFormat(pwd));
        user.setModifyDate(DateUtil.date());
        user.setModifyUserId(getLoginId());
        user.setModifyUserName(getLoginName());

        response.put("result",userService.updateModel(user));

        return response;
    }

    @ApiOperation(value = "更改用户状态")
    @PostMapping("updateUserStatus")
    public BaseResponse updateUserStatus(@Valid @NotBlank(message = "用户id不能为空") String id,@Valid @NotBlank(message = "密码不能为空") String status){
        BaseResponse response = new BaseResponse();
        User user = userService.queryModel(id);

        if(Arrays.asList(LoginStatus.DELETE.getValue(),LoginStatus.NORMAL.getValue(),LoginStatus.FORBIDDEN.getValue()).indexOf(status)<0){
            throw new AuthorityBizException("状态只能更新为【正常、禁用、废除】");
        }

        if(user==null){
            throw new AuthorityBizException("用户不存在，更新状态失败");
        }
        user.setStatus(status);
        user.setModifyDate(DateUtil.date());
        user.setModifyUserId(getLoginId());
        user.setModifyUserName(getLoginName());

        response.put("result",userService.updateModel(user));

        return response;
    }

    @PostMapping("loginLoadData")
    @ApiOperation(value = "登陆后数据加载")
    public BaseResponse loginLoadData(){
        LoginLoadDataResp resp = new LoginLoadDataResp();

        LoginModel loginModel = getCurrentLogin();

        additionLoginModel(loginModel,resp);

        // 在Intceptor中附加
        additionLoginModelIntceptor(loginModel,resp);

        // 重新放入缓存
        cached.put(loginModel.getToken(),loginModel);
        resp.setLoginInfo(loginModel);

        return resp;
    }

    private void additionLoginModelIntceptor(LoginModel loginModel, BaseResponse response){
        Map<String, ILoginLoadDataIntceptor> loginLoadDataMap = SpringContextUtils.getCurrentContext().getBeansOfType(ILoginLoadDataIntceptor.class);
        if(loginLoadDataMap==null||loginLoadDataMap.values()==null||loginLoadDataMap.values().isEmpty()){
            return;
        }
        // 按order 排序一下
        List<ILoginLoadDataIntceptor> loginLoadList = loginLoadDataMap.values().stream().sorted(Comparator.comparing(ILoginLoadDataIntceptor::getOrder)).collect(Collectors.toList());

        for (ILoginLoadDataIntceptor loadDataIntceptor : loginLoadList) {
            loadDataIntceptor.additionLoginModel(loginModel,response);
        }
    }

    public void additionLoginModel(LoginModel loginModel, BaseResponse response) {
        List<Role> roles = roleService.queryListRoleByUserId(loginModel.getId());
        List<Group> groups = groupService.queryListByUserId(loginModel.getId());

        List<String> roleIds = null;
        List<String> groupIds = null;
        if(roles!=null&&roles.size()>0) {
            roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        }
        if(groups!=null&&groups.size()>0) {
            groupIds = groups.stream().map(group -> group.getId()).collect(Collectors.toList());
        }

        List<MenuRoute> menuRouteList = menuService.queryMenuRouteFormatByRGIds(roleIds, groupIds);
        response.put("menuRoutes", menuRouteList);

        List<Button> buttons = buttonService.queryListByRGds(roleIds, groupIds);
        response.put("buttons", buttons);

    }

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
}
