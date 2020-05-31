package com.tennetcn.free.authority.data.entity.apimodel.group;


import com.tennetcn.free.authority.data.entity.viewmodel.GroupSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:55:55
 * @comment     权限组
 */

@Data
public class GroupListReq extends BasePagerReq {
    private GroupSearch search;
}
