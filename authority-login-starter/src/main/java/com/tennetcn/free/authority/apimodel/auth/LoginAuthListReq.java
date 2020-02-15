package com.tennetcn.free.authority.apimodel.auth;

import com.tennetcn.free.authority.viewmodel.LoginAuthSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:41:57
 * @comment     登陆授权表
 */

@Data
public class LoginAuthListReq extends BasePagerReq {
    private LoginAuthSearch search;
}