package com.tennetcn.free.authority.apimodel.button;

import com.tennetcn.free.authority.viewmodel.ButtonSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 22:37
 * @comment
 */

@Data
public class ButtonListReq extends BasePagerReq {
    private ButtonSearch search;
}
