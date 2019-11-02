package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.apimodel.rolefunc.RoleFuncListReq;
import com.tennetcn.free.authority.apimodel.rolefunc.SaveRoleFuncReq;
import com.tennetcn.free.authority.model.RoleFunc;
import com.tennetcn.free.authority.service.IRoleFuncService;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:45
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/roleFunc/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="角色功能",value ="角色功能相关的操作")
public class RoleFuncApi extends AuthorityApi {

    @Autowired
    private IRoleFuncService roleFuncService;

    @ApiOperation(value = "获取角色菜单列表")
    @PostMapping("listRoleFunc")
    public BaseResponse list(@RequestBody  @Valid RoleFuncListReq listReq){
        BaseResponse response = new BaseResponse();

        List<RoleFunc> roleFuncs = roleFuncService.queryListBySearch(listReq.getSearch());
        response.put("roleFuncs",roleFuncs);

        return response;
    }




    @ApiOperation(value = "保存一个角色菜单")
    @PostMapping("save")
    public BaseResponse save(@RequestBody  @Valid SaveRoleFuncReq saveRoleFuncReq){
        BaseResponse response = new BaseResponse();

        boolean result = roleFuncService.saveRoleFuncs(saveRoleFuncReq.getRoleId(),saveRoleFuncReq.getRoleFuncs());
        response.put("result",result);

        return response;
    }
}
