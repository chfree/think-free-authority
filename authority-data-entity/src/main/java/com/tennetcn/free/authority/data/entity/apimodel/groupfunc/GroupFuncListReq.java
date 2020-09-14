package com.tennetcn.free.authority.data.entity.apimodel.groupfunc;

import com.tennetcn.free.authority.data.entity.viewmodel.GroupFuncSearch;
import com.tennetcn.free.core.message.web.BaseRequest;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:00
 * @comment
 */

@Data
public class GroupFuncListReq extends BaseRequest {
    private GroupFuncSearch search;
}
