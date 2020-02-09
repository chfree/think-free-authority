package com.tennetcn.free.authority.data.entity.apimodel.role;

import com.tennetcn.free.authority.data.entity.viewmodel.RoleSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
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
