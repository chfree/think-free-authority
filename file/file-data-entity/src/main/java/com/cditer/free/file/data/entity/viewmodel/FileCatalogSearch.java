package com.cditer.free.file.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-09 09:56:03
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
     * 父级id
     */
    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 关系场景描述
     */
    private String relScnDsc;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 图标
     */
    private String icon;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 标记
     */
    private String mark;

    /**
     * 备注
     */
    private String comments;

}