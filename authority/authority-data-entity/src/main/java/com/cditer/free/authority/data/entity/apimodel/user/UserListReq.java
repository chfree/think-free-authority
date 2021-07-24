package com.cditer.free.authority.data.entity.apimodel.user;

import com.cditer.free.authority.data.entity.viewmodel.UserSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-26 20:37
 * @comment
 */

@Data
public class UserListReq extends BasePagerReq {
    private UserSearch search;
}
