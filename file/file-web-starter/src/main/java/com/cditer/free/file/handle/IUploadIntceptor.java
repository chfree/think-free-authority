package com.cditer.free.file.handle;

import com.cditer.free.file.message.UploadIntceptorParam;

public interface IUploadIntceptor {
    void accept(UploadIntceptorParam uploadIntceptorParam);

    String getBsnType();
}
