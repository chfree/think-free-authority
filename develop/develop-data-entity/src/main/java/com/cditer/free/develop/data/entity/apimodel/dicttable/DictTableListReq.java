package com.cditer.free.develop.data.entity.apimodel.dicttable;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.develop.data.entity.viewmodel.DictTableSearch;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 13:57
 * @comment
 */

@Data
public class DictTableListReq extends BasePagerReq {
    private DictTableSearch search;
}
