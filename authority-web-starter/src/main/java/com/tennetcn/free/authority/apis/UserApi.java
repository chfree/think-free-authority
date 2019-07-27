package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.user.SaveUserReq;
import com.tennetcn.free.authority.apimodel.user.UserListReq;
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
 * @create 2019-07-12 00:03
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/authority/user/",produces = "application/json;charset=utf-8")
@Api(tags="用户模块",value ="用户相关的操作" )
public class UserApi extends FirstApi {

    @ApiOperation(value = "获取用户列表")
    @GetMapping("list")
    public BaseResponse list(@Valid UserListReq listReq){
        return null;
    }

    @ApiOperation(value = "获取指定用户")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定用户")
    @GetMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个用户")
    @PostMapping("save")
    public BaseResponse save(SaveUserReq userReq){
        return new BaseResponse();
    }
}
