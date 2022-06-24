package com.cditer.free.message.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-24 12:08:14
 * @comment     消息模板表
 */

@Data
@Entity
@Table(name="base_message_template")
public class MessageTemplate extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 模板名称
     */
    @Column(name="name")
    private String name;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 图标
     */
    @Column(name="icon")
    private String icon;

    /**
     * 模板内容
     */
    @Column(name="content")
    private String content;

    /**
     * 是否启用
     */
    @Column(name="enabled")
    private String enabled;

    /**
     * 类型
     */
    @Column(name="type")
    private String type;

    /**
     * 消息级别
     */
    @Column(name="level")
    private Integer level;

    /**
     * 备注
     */
    @Column(name="rmrk")
    private String rmrk;

    /**
     * 备注1
     */
    @Column(name="rmrk1")
    private String rmrk1;

    /**
     * 添加人
     */
    @Column(name="add_user_id")
    private String addUserId;

    /**
     * 添加时间
     */
    @Column(name="add_date")
    private Date addDate;

}
