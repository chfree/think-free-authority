package com.tennetcn.free.authority.apimodel.user;

import com.tennetcn.free.authority.model.User;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-26 20:46
 * @comment
 */

@Data
public class SaveUserReq extends User {
    @Override
    @NotBlank(message = "姓名不能为空")
    public @NonNull String getName() {
        return super.getName();
    }

    @Override
    public Integer getDeleteMark() {
        return super.getDeleteMark();
    }
}
