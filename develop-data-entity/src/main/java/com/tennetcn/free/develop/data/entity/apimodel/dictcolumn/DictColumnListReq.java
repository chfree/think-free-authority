package com.tennetcn.free.develop.data.entity.apimodel.dictcolumn;

import com.tennetcn.free.core.message.web.BaseRequest;
import com.tennetcn.free.develop.data.entity.viewmodel.DictColumnSearch;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 13:57
 * @comment
 */

@Data
public class DictColumnListReq extends BaseRequest {
    private DictColumnSearch search;
}
