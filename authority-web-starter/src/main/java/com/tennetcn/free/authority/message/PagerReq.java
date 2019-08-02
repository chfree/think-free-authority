package com.tennetcn.free.authority.message;

import com.tennetcn.free.data.message.PagerModel;
import com.tennetcn.free.web.webapi.BaseResponse;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:10
 * @comment
 */

@Data
public class PagerReq extends BaseResponse {
    @NotNull(message = "分页信息不能为空")
    private PagerModel pager;
}
