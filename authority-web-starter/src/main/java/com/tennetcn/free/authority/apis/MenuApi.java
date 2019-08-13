package com.tennetcn.free.authority.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.department.DepartmentListResp;
import com.tennetcn.free.authority.apimodel.menu.MenuListReq;
import com.tennetcn.free.authority.apimodel.menu.MenuListResp;
import com.tennetcn.free.authority.apimodel.menu.SaveMenuReq;
import com.tennetcn.free.authority.service.IMenuService;
import com.tennetcn.free.authority.viewmodel.DepartmentSearch;
import com.tennetcn.free.authority.viewmodel.MenuSearch;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.web.webapi.BaseResponse;
import com.tennetcn.free.web.webapi.FirstApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取菜单列表")
    @GetMapping("list")
    public BaseResponse list(@Valid MenuListReq listReq){
        MenuListResp resp=new MenuListResp();
        resp.setMenuTrees(menuService.queryListTreeFormat(listReq.getSearch()));

        return resp;
    }

    @ApiOperation(value = "获取指定菜单")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "菜单id不能为空") String id){
        BaseResponse response=new BaseResponse();
        response.put("menu",menuService.queryModelTree(id));
        return null;
    }

    @ApiOperation(value = "搜索部门数量")
    @PostMapping("countSearch")
    public BaseResponse get(MenuSearch search){
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
