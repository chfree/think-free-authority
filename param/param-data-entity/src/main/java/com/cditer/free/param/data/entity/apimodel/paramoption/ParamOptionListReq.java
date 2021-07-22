package com.cditer.free.param.data.entity.apimodel.paramoption;

import com.cditer.free.param.data.entity.viewmodel.ParamOptionSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-28 12:17
 * @comment
 */

@Data
public class ParamOptionListReq extends BasePagerReq {
    private ParamOptionSearch search;
}
