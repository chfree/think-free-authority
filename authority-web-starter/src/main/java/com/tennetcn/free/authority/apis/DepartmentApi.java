package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.department.DepartmentListReq;
import com.tennetcn.free.authority.apimodel.department.DepartmentListResp;
import com.tennetcn.free.authority.apimodel.department.SaveDepartmentReq;
import com.tennetcn.free.authority.model.Button;
import com.tennetcn.free.authority.service.IDepartmentService;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.authority.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.viewmodel.DepartmentTree;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取部门信息")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid DepartmentListReq listReq){
        DepartmentListResp resp=new DepartmentListResp();
        resp.setDeptTrees(departmentService.queryListTreeFormat(listReq.getSearch()));

        return resp;
    }

    @ApiOperation(value = "获取指定部门")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "部门id不能为空") String id){
        BaseResponse response=new BaseResponse();

        DepartmentTree deptTree = departmentService.queryModelTree(id);
        response.put("deptTree",deptTree);

        return response;
    }

    @ApiOperation(value = "搜索部门数量")
    @PostMapping("countSearch")
    public BaseResponse get(DepartmentSearch search){
        BaseResponse response=new BaseResponse();

        int count =  departmentService.queryCountBySearch(search);
        response.put("count",count);

        return response;
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
