package com.cditer.free.authority.data.entity.apimodel.role;

import com.cditer.free.authority.data.entity.viewmodel.RoleSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:00
 * @comment
 */

@Data
public class RoleListReq extends BasePagerReq {
    private RoleSearch search;
}
