package com.cditer.free.authority.data.entity.apimodel.business;

import com.cditer.free.authority.data.entity.model.Business;
import com.cditer.free.core.message.web.BasePagerResp;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-29 23:12
 * @comment
 */

@Data
public class BusinessListResp extends BasePagerResp {
    private List<Business> businessList;
}
