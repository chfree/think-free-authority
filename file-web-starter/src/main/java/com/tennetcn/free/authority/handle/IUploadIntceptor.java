package com.tennetcn.free.authority.handle;

import com.tennetcn.free.file.message.UploadIntceptorParam;

public interface IUploadIntceptor {
    void accept(UploadIntceptorParam uploadIntceptorParam);

    String getBsnType();
}
