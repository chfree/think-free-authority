package com.tennetcn.free.security.handle;

import com.tennetcn.free.security.message.LoginModel;
import com.tennetcn.free.web.webapi.BaseResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadIntceptor {
    BaseResponse accept(String type, String category, @RequestParam MultipartFile[] files, LoginModel loginModel);

    String category();
}
