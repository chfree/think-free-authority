package com.tennetcn.free.develop.apimodel.dicttable;

import com.tennetcn.free.develop.viewmodel.DictTableSearch;
import com.tennetcn.free.web.message.BasePagerReq;
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
