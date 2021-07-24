package com.cditer.free.file.data.entity.viewmodel;


import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:19:32
 * @comment     文件信息表
 */

@Data
public class FileInfoSearch{
    /**
     * 主键
     */
    private String fileId;

    /**
     * not-fileId
     */
    private String notFileId;

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

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 上传时间
     */
    private Date uploadDate;

    /**
     * 上传人
     */
    private String uploadUserId;

    /**
     * 上传人名称
     */
    private String uploadUserName;

    /**
     * 删除标记
     */
    private String deleteMark;

}