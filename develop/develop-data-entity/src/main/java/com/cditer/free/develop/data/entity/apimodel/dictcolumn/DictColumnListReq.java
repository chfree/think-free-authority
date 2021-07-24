package com.cditer.free.develop.data.entity.apimodel.dictcolumn;

import com.cditer.free.develop.data.entity.viewmodel.DictColumnSearch;
import com.cditer.free.core.message.web.BaseRequest;
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
