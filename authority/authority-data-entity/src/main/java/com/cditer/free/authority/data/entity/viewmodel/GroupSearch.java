package com.cditer.free.authority.data.entity.viewmodel;

import java.util.Date;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:46:14
 * @comment     权限组
 */

@Data
public class GroupSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称模糊搜索
     */
    private String likeName;

    /**
     * 描述
     */
    private String description;

    /**
     * 逻辑标记
     */
    private String groupMark;

    /**
     * 逻辑标记模糊搜索
     */
    private String likeGroupMark;

    /**
     * 级别
     */
    private String level;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序字段
     */
    private Integer sortCode;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人id
     */
    private String modifyUserId;

    /**
     * 修改人名称
     */
    private String modifyUserName;

}