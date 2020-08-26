package com.tennetcn.free.authority.data.entity.viewmodel;

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
     * 上传时间
     */
    private Date uploadDate;

    /**
     * 删除标记
     */
    private String deleteMark;

}