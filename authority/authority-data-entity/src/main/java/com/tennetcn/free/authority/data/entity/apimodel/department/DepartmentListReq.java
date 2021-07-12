package com.tennetcn.free.authority.data.entity.apimodel.department;

import com.tennetcn.free.authority.data.entity.viewmodel.DepartmentSearch;
import com.tennetcn.free.core.message.web.BaseRequest;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:46
 * @comment
 */

@Data
public class DepartmentListReq extends BaseRequest {
    private DepartmentSearch search;
}
