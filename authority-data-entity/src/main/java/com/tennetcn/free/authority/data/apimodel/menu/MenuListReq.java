package com.tennetcn.free.authority.data.apimodel.menu;

import com.tennetcn.free.authority.data.viewmodel.MenuSearch;
import com.tennetcn.free.core.message.web.BaseRequest;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:57
 * @comment
 */

@Data
public class MenuListReq extends BaseRequest {
    private MenuSearch search;
}
