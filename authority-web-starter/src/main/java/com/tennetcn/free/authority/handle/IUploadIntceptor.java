package com.tennetcn.free.authority.handle;

import com.tennetcn.free.authority.message.UploadIntceptorParam;
import com.tennetcn.free.authority.message.UploadModel;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.security.message.LoginModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadIntceptor {
    void accept(UploadIntceptorParam uploadIntceptorParam);

    String getBsnType();
}
