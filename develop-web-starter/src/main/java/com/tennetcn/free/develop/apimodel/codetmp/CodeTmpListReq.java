package com.tennetcn.free.develop.apimodel.codetmp;

import com.tennetcn.free.core.message.web.BasePagerReq;
import com.tennetcn.free.develop.viewmodel.CodeTmpSearch;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:27:25
 * @comment     代码模板
 */

@Data
public class CodeTmpListReq extends BasePagerReq {
    private CodeTmpSearch search;
}
