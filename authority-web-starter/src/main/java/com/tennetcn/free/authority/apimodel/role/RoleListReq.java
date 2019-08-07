package com.tennetcn.free.authority.apimodel.role;

import com.tennetcn.free.authority.message.PagerReq;
import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.authority.viewmodel.RoleSearch;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:00
 * @comment
 */

@Data
public class RoleListReq extends PagerReq {
    private RoleSearch search;
}
