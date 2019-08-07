package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.role.RoleListReq;
import com.tennetcn.free.authority.apimodel.role.RoleListResp;
import com.tennetcn.free.authority.apimodel.role.SaveRoleReq;
import com.tennetcn.free.authority.model.Role;
import com.tennetcn.free.authority.service.IRoleService;
import com.tennetcn.free.authority.viewmodel.RoleSearch;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:42
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/role/",produces = "application/json;charset=utf-8")
@Api(tags="角色管理",value ="角色相关的操作")
public class RoleApi extends FirstApi {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping("list")
    public BaseResponse list(@RequestBody  @Valid RoleListReq listReq){
        RoleListResp resp = new RoleListResp();
        resp.setTotalCount(roleService.queryCountBySearch(listReq.getSearch()));
        resp.setRoles(roleService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定角色")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "角色id不能为空") String id){
        BaseResponse response=new BaseResponse();

        Role role = roleService.queryModel(id);
        response.put("role",role);

        return response;
    }

    @ApiOperation(value = "搜索角色数量")
    @PostMapping("countSearch")
    public BaseResponse get(RoleSearch search){
        BaseResponse response=new BaseResponse();

        int count =  roleService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定角色")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个角色")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveRoleReq saveRoleReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveRoleReq.getId())){
            saveRoleReq.setId(IdUtil.randomUUID());
            saveRoleReq.setModelStatus(ModelStatus.add);
        }else{
            saveRoleReq.setModelStatus(ModelStatus.update);
        }

        boolean result = roleService.applyChange(saveRoleReq);
        response.put("result",result);

        return response;
    }
}
