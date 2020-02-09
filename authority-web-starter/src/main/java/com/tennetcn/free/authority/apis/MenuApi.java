package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.data.entity.apimodel.menu.MenuListReq;
import com.tennetcn.free.authority.data.entity.apimodel.menu.MenuListResp;
import com.tennetcn.free.authority.data.entity.apimodel.menu.SaveMenuReq;
import com.tennetcn.free.authority.service.IMenuService;
import com.tennetcn.free.authority.service.IRoleService;
import com.tennetcn.free.authority.data.entity.viewmodel.MenuSearch;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
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

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:43
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/menu/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="菜单管理",value ="菜单相关的操作")
public class MenuApi extends AuthorityApi {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

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
}
