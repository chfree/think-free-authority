package com.cditer.free.message.entity.model;


import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:27:21
 * @comment     消息组
 */

@Data
@Entity
@Table(name="base_message_group")
public class ReceiveGroup extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 名称
     */
    @Column(name="name")
    private String name;

    /**
     * 标题
     */
    @Column(name="title")
    private String title;

    /**
     * 状态
     */
    @Column(name="status")
    private String status;

}
