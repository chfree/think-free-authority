package com.tennetcn.free.authority.apimodel.business;

import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import com.tennetcn.free.web.message.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:53
 * @comment
 */

@Data
public class BusinessListReq extends BasePagerReq {
    private BusinessSearch search;
}
