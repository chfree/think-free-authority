package com.cditer.free.message.entity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-26 15:47:11
 * @comment     消息表
 */

@Data
@Entity
@Table(name="base_message_info")
public class MessageInfo extends ModelBase {
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 正文
     */
    @Column(name="content")
    private String content;

    /**
     * 显示模式
     */
    @Column(name="show_mode")
    private String showMode;


    /**
     * 标题图标
     */
    @Column(name="icon")
    private String icon;

    /**
     * 开始提示时间
     */
    @Column(name="start_dt")
    private Date startDt;

    /**
     * 消息结束时间
     */
    @Column(name="end_dt")
    private Date endDt;

    /**
     * 消息类型
     */
    @Column(name="type")
    private String type;

    /**
     * 业务数据
     */
    @Column(name="bus_data")
    private String busData;

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
