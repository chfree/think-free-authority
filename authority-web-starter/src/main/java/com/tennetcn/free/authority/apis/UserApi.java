package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.login.LoginLoadDataResp;
import com.tennetcn.free.authority.data.entity.apimodel.user.SaveUserReq;
import com.tennetcn.free.authority.data.entity.apimodel.user.UpdatePwd;
import com.tennetcn.free.authority.data.entity.apimodel.user.UserListReq;
import com.tennetcn.free.authority.data.entity.apimodel.user.UserListResp;
import com.tennetcn.free.authority.data.entity.model.*;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuRoute;
import com.tennetcn.free.authority.data.entity.viewmodel.UserSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.UserView;
import com.tennetcn.free.authority.enums.LoginStatus;
import com.tennetcn.free.authority.exception.AuthorityBizException;
import com.tennetcn.free.authority.handle.ILoginLoadDataIntceptor;
import com.tennetcn.free.authority.service.*;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.core.util.SpringContextUtils;
import com.tennetcn.free.security.handle.ILoginModelIntceptor;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.message.WebResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-12 00:03
 * @comment
 */

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
    IDepartmentService departmentService;

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
    public BaseResponse get(@Valid @NotBlank(message = "用户id不能为空") String id){
        BaseResponse response=new BaseResponse();

        UserView user = userService.queryViewModelById(id);
        response.put("user",user);

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
        Map<String, ILoginLoadDataIntceptor> beansOfType = SpringContextUtils.getCurrentContext().getBeansOfType(ILoginLoadDataIntceptor.class);
        if(beansOfType==null||beansOfType.values()==null||beansOfType.values().isEmpty()){
            return;
        }
        for (ILoginLoadDataIntceptor loadDataIntceptor : beansOfType.values()) {
            loadDataIntceptor.additionLoginModel(loginModel,response);
        }
    }

    public void additionLoginModel(LoginModel loginModel, BaseResponse response) {
        List<Role> roles = roleService.queryListRoleByUserId(loginModel.getId());
        List<Group> groups = groupService.queryListByUserId(loginModel.getId());

        loginModel.put("roles", roles);
        loginModel.put("groups", groups);

        Department department = departmentService.queryModel(loginModel.getString("departmentId"));
        if(department!=null){
            loginModel.put("departmentName",department.getFullName());
            loginModel.put("department",department);
            loginModel.setCurrentDeptId(department.getId());
            loginModel.setCurrentDeptName(department.getShortName());
        }

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
}
