package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.role.RoleListReq;
import com.tennetcn.free.authority.apimodel.role.SaveRoleReq;
import com.tennetcn.free.authority.apimodel.rolemenu.RoleMenuListReq;
import com.tennetcn.free.authority.apimodel.rolemenu.SaveRoleMenuReq;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:45
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/roleMenu/",produces = "application/json;charset=utf-8")
@Api(tags="角色菜单",value ="角色菜单相关的操作")
public class RoleMenuApi extends FirstApi {
    @ApiOperation(value = "获取角色菜单列表")
    @GetMapping("list")
    public BaseResponse list(@Valid RoleMenuListReq listReq){
        return null;
    }

    @ApiOperation(value = "获取指定角色菜单")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定角色菜单")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个角色菜单")
    @PostMapping("save")
    public BaseResponse save(SaveRoleMenuReq saveRoleMenuReq){
        return new BaseResponse();
    }
}
