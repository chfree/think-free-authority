package com.tennetcn.free.authority.apimodel.paramdefine;

import com.tennetcn.free.authority.message.PagerReq;
import com.tennetcn.free.authority.viewmodel.ParamDefineSearch;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-28 12:17
 * @comment
 */

@Data
public class ParamDefineListReq extends PagerReq {
    private ParamDefineSearch search;
}
