package com.tennetcn.free.authority.apimodel.user;

import com.tennetcn.free.authority.viewmodel.UserView;
import com.tennetcn.free.core.message.web.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class UserListResp extends BasePagerResp {
   private List<UserView> users;
}
