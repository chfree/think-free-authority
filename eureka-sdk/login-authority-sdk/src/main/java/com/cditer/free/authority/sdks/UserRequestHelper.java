package com.cditer.free.authority.sdks;

import com.cditer.cloud.commons.http.entity.RequestEntityEx;
import com.cditer.cloud.commons.http.entity.RequestEntityExBuilder;
import com.cditer.cloud.commons.http.entity.ResponseEntityEx;
import com.cditer.cloud.commons.http.request.impl.BaseServerRequest;
import com.cditer.free.core.message.web.BaseResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-09 12:30
 * @comment
 */

@Component
public class UserRequestHelper extends BaseServerRequest {
    final static String prefix = "/api/v1/authority/user/";

    private final static String getUrl = prefix +"get";

    public BaseResponse getUser(String userId){

        RequestEntityEx requestEntityEx = RequestEntityExBuilder.builder()
                .setUrl(getUrl)
                .setMethod(HttpMethod.GET)
                .addParam("id", userId)
                .build();

        ResponseEntityEx<BaseResponse> request = getServerRequestHelper().request(requestEntityEx, BaseResponse.class);

        return request.getBody();
    }
}
