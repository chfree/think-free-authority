package com.tennetcn.free.authority.apimodel.business;

import com.tennetcn.free.authority.message.PagerReq;
import com.tennetcn.free.authority.viewmodel.BusinessSearch;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 12:53
 * @comment
 */

@Data
public class BusinessListReq extends PagerReq {
    private BusinessSearch search;
}
