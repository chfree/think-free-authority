package com.cditer.free.authority.entity.apimodel.auth;

import com.cditer.free.authority.entity.viewmodel.LoginAuthView;
import com.cditer.free.core.message.web.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-15 14:42:36
 * @comment     登陆授权表
 */

@Data
public class LoginAuthListResp extends BasePagerResp {
    private List<LoginAuthView> loginAuths;
}
