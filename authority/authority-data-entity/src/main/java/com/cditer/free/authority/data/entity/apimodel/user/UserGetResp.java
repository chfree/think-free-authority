package com.cditer.free.authority.data.entity.apimodel.user;

import com.cditer.free.authority.data.entity.model.User;
import com.cditer.free.core.message.web.BaseResponse;
import lombok.Data;

@Data
public class UserGetResp extends BaseResponse {
    private User user;
}
