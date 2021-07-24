package com.cditer.free.authority.data.entity.apimodel.department;

import com.cditer.free.authority.data.entity.viewmodel.DepartmentSearch;
import com.cditer.free.core.message.web.BaseRequest;
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
