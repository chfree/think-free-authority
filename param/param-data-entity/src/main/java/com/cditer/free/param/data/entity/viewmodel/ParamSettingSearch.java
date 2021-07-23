package com.cditer.free.param.data.entity.viewmodel;

import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 11:26:27
 * @comment     参数配置表
 */

@Data
public class ParamSettingSearch{
    /**
     * 主键
     */
    private String id;

    /**
     * not-id
     */
    private String notId;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数标题
     */
    private String title;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 备注
     */
    private String comments;

    /**
     * 参数名称
     */
    private String likeName;

    /**
     * 参数标题
     */
    private String likeTitle;

}