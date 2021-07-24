package com.cditer.free.authority.data.entity.viewmodel;

import com.cditer.free.authority.data.entity.model.Department;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-09 12:39
 * @comment
 */

@Data
public class DepartmentTree extends Department {
    private List<DepartmentTree> children;

    private String parentName;
}
