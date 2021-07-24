package com.cditer.free.authority.data.entity.apimodel.rolefunc;

import com.cditer.free.authority.data.entity.viewmodel.RoleFuncSearch;
import com.cditer.free.core.message.web.BaseRequest;
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
