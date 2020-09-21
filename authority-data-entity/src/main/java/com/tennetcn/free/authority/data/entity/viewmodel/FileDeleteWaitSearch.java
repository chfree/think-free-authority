package com.tennetcn.free.authority.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 11:54:39
 * @comment     文件删除等待表
 */

@Data
public class FileDeleteWaitSearch{
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
     * 上传时间
     */
    private Date uploadDate;

    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 添加时间
     */
    private Date addDate;

    /**
     * 等待时间
     */
    private Integer waitDay;

}