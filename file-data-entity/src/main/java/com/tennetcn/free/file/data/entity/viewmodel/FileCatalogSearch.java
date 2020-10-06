package com.tennetcn.free.file.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:13:18
 * @comment     文件目录表
 */

@Data
public class FileCatalogSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String comments;

}