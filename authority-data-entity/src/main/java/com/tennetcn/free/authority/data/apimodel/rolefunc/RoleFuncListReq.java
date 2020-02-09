package com.tennetcn.free.authority.data.apimodel.rolefunc;

import com.tennetcn.free.authority.data.viewmodel.RoleFuncSearch;
import com.tennetcn.free.core.message.web.BaseRequest;
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
