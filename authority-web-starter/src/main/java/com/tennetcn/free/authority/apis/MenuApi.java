package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.menu.MenuListReq;
import com.tennetcn.free.authority.apimodel.menu.SaveMenuReq;
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
 * @create 2019-07-29 12:43
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/menu/",produces = "application/json;charset=utf-8")
@Api(tags="菜单管理",value ="菜单相关的操作")
public class MenuApi extends FirstApi {
    @ApiOperation(value = "获取菜单列表")
    @GetMapping("list")
    public BaseResponse list(@Valid MenuListReq listReq){
        return null;
    }

    @ApiOperation(value = "获取指定菜单")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定菜单")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个菜单")
    @PostMapping("save")
    public BaseResponse save(SaveMenuReq saveMenuReq){
        return new BaseResponse();
    }
}
