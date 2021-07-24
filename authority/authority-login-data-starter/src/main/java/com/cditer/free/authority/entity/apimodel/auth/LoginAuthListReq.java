package com.cditer.free.authority.entity.apimodel.auth;

import com.cditer.free.authority.entity.viewmodel.LoginAuthSearch;
import com.cditer.free.core.message.web.BasePagerReq;
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