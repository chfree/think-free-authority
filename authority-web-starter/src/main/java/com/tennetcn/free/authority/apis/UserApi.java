package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.login.LoginLoadDataResp;
import com.tennetcn.free.authority.data.entity.apimodel.user.SaveUserReq;
import com.tennetcn.free.authority.data.entity.apimodel.user.UserListReq;
import com.tennetcn.free.authority.data.entity.apimodel.user.UserListResp;
import com.tennetcn.free.authority.data.entity.model.Button;
import com.tennetcn.free.authority.data.entity.model.Department;
import com.tennetcn.free.authority.data.entity.model.Role;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuRoute;
import com.tennetcn.free.authority.data.entity.viewmodel.UserSearch;
import com.tennetcn.free.authority.data.entity.viewmodel.UserView;
import com.tennetcn.free.authority.service.*;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
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
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IButtonService buttonService;

    @Autowired
    private IDepartmentService departmentService;

    public UserApi() {
    }

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
        }else{
            userReq.setModelStatus(ModelStatus.update);
        }

        boolean result = userService.applyChange(userReq);
        response.put("result",result);

        return response;
    }

    @PostMapping("loginLoadData")
    public BaseResponse loginLoadData(){
        LoginLoadDataResp resp = new LoginLoadDataResp();

        LoginModel loginModel = getCurrentLogin();

        additionLoginModel(loginModel,resp);

        // 重新放入缓存
        cached.put(loginModel.getToken(),loginModel);
        resp.setLoginInfo(loginModel);

        return resp;
    }

    public void additionLoginModel(LoginModel loginModel, BaseResponse response) {
        List<Role> roles = roleService.queryListRoleByUserId(loginModel.getId());
        loginModel.put("roles", roles);

        Department department = departmentService.queryModel(loginModel.getString("departmentId"));
        if(department!=null){
            loginModel.put("departmentName",department.getFullName());
            loginModel.put("department",department);
        }

        if(roles!=null&&roles.size()>0){
            List<String> roleIds = roles.stream().map(role-> role.getId()).collect(Collectors.toList());

            List<MenuRoute> menuRouteList = menuService.queryMenuRouteFormatByRoleIds(roleIds);
            response.put("menuRoutes", menuRouteList);

            List<Button> buttons = buttonService.queryListByRoleIds(roleIds);
            response.put("buttons", buttons);
        }
    }
}
