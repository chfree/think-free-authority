package com.cditer.free.authority.data.entity.apimodel.group;


import com.cditer.free.authority.data.entity.viewmodel.GroupSearch;
import com.cditer.free.core.message.web.BasePagerReq;
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
