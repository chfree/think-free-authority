package com.cditer.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.cditer.free.authority.data.entity.apimodel.menu.MenuListReq;
import com.cditer.free.authority.data.entity.apimodel.menu.MenuListResp;
import com.cditer.free.authority.data.entity.apimodel.menu.SaveMenuReq;
import com.cditer.free.authority.data.entity.model.Group;
import com.cditer.free.authority.data.entity.model.Menu;
import com.cditer.free.authority.data.entity.model.Role;
import com.cditer.free.authority.data.entity.viewmodel.MenuSearch;
import com.cditer.free.authority.logical.service.IGroupService;
import com.cditer.free.authority.logical.service.IMenuService;
import com.cditer.free.authority.logical.service.IRoleService;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.core.message.security.LoginModel;
import com.cditer.free.security.baseapi.TokenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:43
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/menu/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="菜单管理",value ="菜单相关的操作")
public class MenuApi extends TokenApi {

    @Autowired
    IMenuService menuService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IGroupService groupService;

    @ApiOperation(value = "获取菜单列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid MenuListReq listReq){
        MenuListResp resp=new MenuListResp();
        resp.setMenuTrees(menuService.queryListTreeFormat(listReq.getSearch()));

        return resp;
    }

    @ApiOperation(value = "根据登陆信息获取route菜单列表")
    @PostMapping("listRouteByLogin")
    public BaseResponse listRouteByLogin(){
        BaseResponse resp=new BaseResponse();
        List<String> roleIds=roleService.queryListByUserId(getLoginId());
        resp.put("menuRoutes",menuService.queryMenuRouteFormatByRoleIds(roleIds));

        return resp;
    }

    @ApiOperation(value = "获取route菜单列表")
    @PostMapping("listRoute")
    public BaseResponse listRoute(){
        BaseResponse resp=new BaseResponse();
        resp.put("menuRoutes",menuService.queryMenuRouteFormatByRoleIds(null));

        return resp;
    }

    @ApiOperation(value = "获取指定菜单")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "菜单id不能为空") String id){
        BaseResponse response=new BaseResponse();
        response.put("menuTree",menuService.queryModelTree(id));
        return response;
    }

    @ApiOperation(value = "搜索部门数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(MenuSearch search){
        BaseResponse response=new BaseResponse();

        int count =  menuService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定菜单")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个菜单")
    @PostMapping("save")
    public BaseResponse save(SaveMenuReq saveMenuReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveMenuReq.getId())){
            saveMenuReq.setId(IdUtil.randomUUID());
            saveMenuReq.setModelStatus(ModelStatus.add);
        }else{
            saveMenuReq.setModelStatus(ModelStatus.update);
        }

        boolean result = menuService.applyChange(saveMenuReq);
        response.put("result",result);

        return response;
    }

    @ApiOperation(value = "根据服务名称获取菜单")
    @PostMapping("queryMenusByServerName")
    public BaseResponse queryMenusByServerName(@Valid @NotBlank(message = "服务名称不能为空") String serverName){
        BaseResponse resp=new BaseResponse();

        LoginModel currentLogin = getCurrentLogin();
        List<Role> roles = roleService.queryListRoleByUserId(currentLogin.getId());
        List<Group> groups = groupService.queryListByUserId(currentLogin.getId());

        List<String> roleIds = null;
        List<String> groupIds = null;
        if(!CollectionUtils.isEmpty(roles)) {
            roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        }
        if(!CollectionUtils.isEmpty(groups)) {
            groupIds = groups.stream().map(group -> group.getId()).collect(Collectors.toList());
        }

        List<Menu> menus = menuService.queryMenuBySeverNameAndRGIds(serverName, roleIds, groupIds);
        resp.put("menus", menus);

        return resp;
    }
}
