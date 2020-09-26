package com.tennetcn.free.file.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:24:58
 * @comment     文件业务表
 */

@Data
public class FileBsnSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 业务id
     */
    private String bsnId;

    /**
     * 业务类型
     */
    private String bsnType;

    /**
     * 上传人
     */
    private String uploadUserId;

    /**
     * 上传人名称
     */
    private String uploadUserName;

    /**
     * 上传时间-开始
     */
    private Date startUploadDate;

    /**
     * 上传时间-结束
     */
    private Date endUploadDate;

    /**
     * 文件名称
     */
    private String displayName;

    /**
     * 删除标记
     */
    private String deleteMark;

    /**
     * 文件类型
     */
    private String mimeType;

    /**
     * 文件的sha1
     */
    private String sha1;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 文件名称 模糊搜索
     */
    private String likeDisplayName;

    /**
     * 上传人 模糊搜索
     */
    private String likeUploadUserName;
}