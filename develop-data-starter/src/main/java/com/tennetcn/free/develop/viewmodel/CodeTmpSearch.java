package com.tennetcn.free.develop.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:06:03
 * @comment     代码模板
 */

@Data
public class CodeTmpSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 模板名称模糊搜索
     */
    private String likeName;

    /**
     * 模板描述
     */
    private String comment;

    /**
     * 模板内容
     */
    private String content;

    /**
     * 是否公开
     */
    private String pub;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型模糊搜索
     */
    private String likeType;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组名称模糊搜索
     */
    private String likeGroupName;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建人名称
     */
    private String createUserName;

}