package com.cditer.free.authority.data.entity.apimodel.user;

import com.cditer.free.authority.data.entity.model.User;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-26 20:46
 * @comment
 */

@Data
public class SaveUserReq extends User {

    @Valid @NotBlank(message = "姓名不能为空")
    private String name;

    @Valid @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 角色集合
     */
    private List<String> roleIds;

    /**
     * 用户组集合
     */
    private List<String> groupIds;

}
