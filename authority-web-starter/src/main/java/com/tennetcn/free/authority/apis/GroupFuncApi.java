package com.tennetcn.free.authority.apis;

import com.tennetcn.free.authority.data.entity.apimodel.groupfunc.rolefunc.GroupFuncListReq;
import com.tennetcn.free.authority.data.entity.apimodel.groupfunc.rolefunc.SaveGroupFuncReq;
import com.tennetcn.free.authority.data.entity.apimodel.rolefunc.RoleFuncListReq;
import com.tennetcn.free.authority.data.entity.apimodel.rolefunc.SaveRoleFuncReq;
import com.tennetcn.free.authority.data.entity.model.GroupFunc;
import com.tennetcn.free.authority.data.entity.model.RoleFunc;
import com.tennetcn.free.authority.service.IGroupFuncService;
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
@RequestMapping(value = "/api/v1/authority/groupFunc/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="分组功能",value ="分组功能相关的操作")
public class GroupFuncApi extends AuthorityApi {

    @Autowired
    private IGroupFuncService groupFuncService;

    @ApiOperation(value = "获取分组菜单列表")
    @PostMapping("listGroupFunc")
    public BaseResponse list(@RequestBody  @Valid GroupFuncListReq listReq){
        BaseResponse response = new BaseResponse();

        List<GroupFunc> groupFuncs = groupFuncService.queryListBySearch(listReq.getSearch());
        response.put("groupFuncs",groupFuncs);

        return response;
    }




    @ApiOperation(value = "保存分组菜单")
    @PostMapping("save")
    public BaseResponse save(@RequestBody  @Valid SaveGroupFuncReq saveGroupFuncReq){
        BaseResponse response = new BaseResponse();

        boolean result = groupFuncService.saveGroupFuncs(saveGroupFuncReq.getGroupId(),saveGroupFuncReq.getGroupFuncs());
        response.put("result",result);

        return response;
    }
}
