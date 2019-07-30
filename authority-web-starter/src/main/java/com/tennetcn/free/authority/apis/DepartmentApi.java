package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.department.DepartmentListReq;
import com.tennetcn.free.authority.apimodel.department.SaveDepartmentReq;
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
@RequestMapping(value = "/api/v1/authority/department/",produces = "application/json;charset=utf-8")
@Api(tags="部门管理",value ="部门相关的操作")
public class DepartmentApi extends FirstApi {
    @ApiOperation(value = "获取部门列表")
    @GetMapping("list")
    public BaseResponse list(@Valid DepartmentListReq listReq){
        return null;
    }

    @ApiOperation(value = "获取指定部门")
    @GetMapping("get")
    public BaseResponse get(@Valid String id){
        return null;
    }

    @ApiOperation(value = "删除指定部门")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个部门")
    @PostMapping("save")
    public BaseResponse save(SaveDepartmentReq saveDepartmentReq){
        return new BaseResponse();
    }
}
