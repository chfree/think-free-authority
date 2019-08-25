package com.tennetcn.free.authority.apis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.authority.apimodel.menubutton.SaveMenuButtonReq;
import com.tennetcn.free.authority.model.MenuButton;
import com.tennetcn.free.authority.service.IMenuButtonService;
import com.tennetcn.free.authority.viewmodel.MenuButtonSearch;
import com.tennetcn.free.data.enums.YesOrNoInteger;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:45
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/authority/menuButton/",produces = "application/json;charset=utf-8")
@Api(tags="菜单按钮",value ="菜单按钮相关的操作")
public class MenuButtonApi extends AuthorityApi {

    @Autowired
    private IMenuButtonService menuButtonService;

    @ApiOperation(value = "获取菜单按钮的格式化数据")
    @GetMapping("listMenuButton")
    public BaseResponse listFormat(){
        BaseResponse response = new BaseResponse();

        response.put("menuButtons",menuButtonService.queryListTreeFormat());

        return response;
    }

    @ApiOperation(value = "根据菜单id获取按钮信息")
    @GetMapping("listButton")
    public BaseResponse listButton(@Valid @NotBlank(message = "菜单id不能为空") String menuId){
        BaseResponse response = new BaseResponse();
        MenuButtonSearch mbSearch=new MenuButtonSearch();
        mbSearch.setMenuId(menuId);

        response.put("buttons",menuButtonService.queryListBySearch(mbSearch,null));

        return response;
    }

    @ApiOperation(value = "保存菜单按钮")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid SaveMenuButtonReq saveMenuButtonReq){
        BaseResponse response = new BaseResponse();
        var menuButtons = menuButtonForm(saveMenuButtonReq.getMenuButtons());
        boolean result = menuButtonService.saveMenuButtons(saveMenuButtonReq.getMenuId(),menuButtons);
        response.put("result", result);

        return response;
    }

    @ApiOperation(value = "搜索菜单按钮数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(MenuButtonSearch search){
        BaseResponse response=new BaseResponse();

        int count =  menuButtonService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    private List<MenuButton> menuButtonForm(List<MenuButton> menuButtons){
        return menuButtons.stream().map(menuButton -> {
            menuButton.setCreateDate(DateUtil.date());
            menuButton.setId(IdUtil.randomUUID());
            menuButton.setCreateUserId(null);
            menuButton.setCreateUserName(null);
            menuButton.setDeleteMark(YesOrNoInteger.NO);

            return menuButton;
        }).collect(Collectors.toList());
    }
}
