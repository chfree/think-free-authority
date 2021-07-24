package com.cditer.free.authority.data.entity.apimodel.department;

import com.cditer.free.authority.data.entity.model.Department;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:46
 * @comment
 */

@Data
public class SaveDepartmentReq extends Department {

    @NotBlank(message = "部门名称不能为空")
    private String fullName;
}
