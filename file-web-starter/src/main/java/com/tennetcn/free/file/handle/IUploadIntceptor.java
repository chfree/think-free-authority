package com.tennetcn.free.file.handle;

import com.tennetcn.free.file.message.UploadIntceptorParam;

public interface IUploadIntceptor {
    void accept(UploadIntceptorParam uploadIntceptorParam);

    String getBsnType();
}
