package com.cditer.free.file.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 23:29:10
 * @comment     文件模板表
 */

@Data
public class FileTemplateSearch{
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
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

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
     * 上传人名称 模糊搜索
     */
    private String likeUploadUserName;

    /**
     * title 模糊搜索
     */
    private String likeTitle;

    /**
     * name 模糊搜索
     */
    private String likeName;

}