package com.tennetcn.free.file.exception;

import com.tennetcn.free.core.exception.BizException;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-26 11:44
 * @comment
 */

public class FileBizException extends BizException {
    public FileBizException(){}

    public FileBizException(String message){
        super(message);
    }

    public FileBizException(Throwable throwable){
        super("FileBizException",throwable);
    }

    public FileBizException(String message,Throwable throwable){
        super(message,throwable);
    }

    public FileBizException(int code, String message){
        super(code,message);
    }
}
