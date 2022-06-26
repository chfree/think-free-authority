package com.cditer.free.message.entity.model;


import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2022-06-26 15:49:40
 * @comment     消息接收人
 */

@Data
@Entity
@Table(name="base_message_receive")
public class MessageReceive extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 消息id
     */
    @Column(name="message_id")
    private String messageId;

    /**
     * 接收范围
     */
    @Column(name="receive_type")
    private String receiveType;

    /**
     * 接收id
     */
    @Column(name="receive_id")
    private String receiveId;

}