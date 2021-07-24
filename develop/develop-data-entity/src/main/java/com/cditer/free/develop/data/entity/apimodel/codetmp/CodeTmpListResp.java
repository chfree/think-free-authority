package com.cditer.free.develop.data.entity.apimodel.codetmp;

import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.develop.data.entity.model.CodeTmp;
import lombok.Data;

import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:28:09
 * @comment     代码模板
 */

@Data
public class CodeTmpListResp extends BasePagerResp {
    private List<CodeTmp> codeTmps;
}