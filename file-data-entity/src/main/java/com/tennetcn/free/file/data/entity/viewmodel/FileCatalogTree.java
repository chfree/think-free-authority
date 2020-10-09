package com.tennetcn.free.file.data.entity.viewmodel;

import com.tennetcn.free.file.data.entity.model.FileCatalog;
import lombok.Data;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-10-09 10:48
 * @comment
 */

@Data
public class FileCatalogTree extends FileCatalog {
    /**
     * 子级目录
     */
    private List<FileCatalogTree> children;
}
