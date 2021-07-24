package com.cditer.free.param.data.entity.apimodel.paramdefine;

import com.cditer.free.param.data.entity.viewmodel.ParamDefineSearch;
import com.cditer.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-28 12:17
 * @comment
 */

@Data
public class ParamDefineListReq extends BasePagerReq {
    private ParamDefineSearch search;
}
