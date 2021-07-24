package com.cditer.free.authority.data.entity.apimodel.department;

import com.cditer.free.authority.data.entity.viewmodel.DepartmentTree;
import com.cditer.free.core.message.web.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class DepartmentListResp extends BaseResponse {
    private List<DepartmentTree> deptTrees;
}
