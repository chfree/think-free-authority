package com.cditer.free.authority.data.entity.apimodel.button;

import com.cditer.free.authority.data.entity.viewmodel.ButtonSearch;
import com.cditer.free.core.message.web.BasePagerReq;
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
