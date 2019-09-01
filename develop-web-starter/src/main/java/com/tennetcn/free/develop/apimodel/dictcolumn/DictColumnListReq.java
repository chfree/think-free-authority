package com.tennetcn.free.develop.apimodel.dictcolumn;

import com.tennetcn.free.develop.viewmodel.DictColumnSearch;
import com.tennetcn.free.web.message.BasePagerReq;
import com.tennetcn.free.web.webapi.BaseRequest;
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
