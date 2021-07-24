package com.cditer.free.file.message;

import com.cditer.free.core.message.web.BaseResponse;
import com.cditer.free.security.message.LoginModel;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-08-26 19:43
 * @comment
 */

@Data
public class UploadIntceptorParam {
    /**
     * 单个上传还是多个上传
     */
    private String type;

    /**
     * 业务类型，与接口的实现一致
     */
    private String bsnType;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 业务json数据，不会被fileBsn存储
     */
    private String bsnJson;

    /**
     * 登陆实体对象
     */
    private LoginModel loginModel;

    /**
     * 回调
     */
    private BaseResponse response;

    /**
     * 上传的信息
     */
    private List<UploadModel> uploadModels;
}
