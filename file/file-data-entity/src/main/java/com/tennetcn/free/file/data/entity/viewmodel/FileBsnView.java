package com.tennetcn.free.file.data.entity.viewmodel;

import com.tennetcn.free.file.data.entity.model.FileBsn;
import lombok.Data;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-14 21:56
 * @comment
 */
@Data
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
