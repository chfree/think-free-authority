package com.tennetcn.free.authority.data.viewmodel;

import com.tennetcn.free.authority.data.model.User;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-19 12:23
 * @comment
 */

@Data
public class UserView extends User {
    private String departmentName;
}
