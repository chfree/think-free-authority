package com.tennetcn.free.authority.apimodel.menu;

import com.tennetcn.free.authority.viewmodel.DepartmentTree;
import com.tennetcn.free.authority.viewmodel.MenuTree;
import com.tennetcn.free.web.webapi.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class MenuListResp extends BaseResponse {
    private List<MenuTree> menuTrees;
}
