package com.tennetcn.free.authority.data.entity.viewmodel;

import com.tennetcn.free.authority.data.entity.model.FileBsn;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-14 21:56
 * @comment
 */

public class FileBsnView extends FileBsn {
    /**
     * 大小
     */
    private Integer size;

    /**
     * 文件类型
     */
    private String mimeType;

    /**
     * 文件的sha1
     */
    private String sha1;

    /**
     * 存储方式
     */
    private String storeType;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 路径
     */
    private String path;
}
