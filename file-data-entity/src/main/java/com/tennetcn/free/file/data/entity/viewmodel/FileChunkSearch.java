package com.tennetcn.free.file.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:26:41
 * @comment     文件块
 */

@Data
public class FileChunkSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 当前文件块
     */
    private Integer chunkNumber;

    /**
     * 分块大小
     */
    private String chunkSize;

    /**
     * 当前分块大小
     */
    private String currentChunkSize;

    /**
     * 总大小
     */
    private String totalSize;

    /**
     * 文件标识
     */
    private String identifier;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 总块数
     */
    private Integer totalChunks;

    /**
     * 文件类型
     */
    private String mimeType;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 业务类型
     */
    private String bsnType;

    /**
     * 状态
     */
    private String status;

}