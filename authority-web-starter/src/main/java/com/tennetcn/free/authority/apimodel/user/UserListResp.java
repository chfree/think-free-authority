package com.tennetcn.free.authority.apimodel.user;

import com.tennetcn.free.authority.message.PagerResp;
import com.tennetcn.free.authority.model.Role;
import com.tennetcn.free.authority.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class UserListResp extends PagerResp {
   private List<User> users;
}
