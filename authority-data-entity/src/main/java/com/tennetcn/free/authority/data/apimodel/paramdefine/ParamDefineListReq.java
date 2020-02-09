package com.tennetcn.free.authority.data.apimodel.paramdefine;

import com.tennetcn.free.authority.data.viewmodel.ParamDefineSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
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
