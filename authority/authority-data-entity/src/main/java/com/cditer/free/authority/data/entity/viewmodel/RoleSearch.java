package com.cditer.free.authority.data.entity.viewmodel;

import com.cditer.free.authority.data.entity.model.Role;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-07 12:34
 * @comment
 */

@Data
public class RoleSearch  extends Role {
    private String notId;

    private String likeRoleName;
}
