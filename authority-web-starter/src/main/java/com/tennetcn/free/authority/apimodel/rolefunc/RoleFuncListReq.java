package com.tennetcn.free.authority.apimodel.rolefunc;

import com.tennetcn.free.authority.viewmodel.RoleFuncSearch;
import com.tennetcn.free.web.webapi.BaseRequest;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:00
 * @comment
 */

@Data
public class RoleFuncListReq extends BaseRequest {
    private RoleFuncSearch search;
}
