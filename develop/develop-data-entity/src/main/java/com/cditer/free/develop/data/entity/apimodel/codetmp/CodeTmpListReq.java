package com.cditer.free.develop.data.entity.apimodel.codetmp;

import com.cditer.free.develop.data.entity.viewmodel.CodeTmpSearch;
import com.cditer.free.core.message.web.BasePagerReq;
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
