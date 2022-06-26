package com.cditer.free.message.entity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-26 17:09:10
 * @comment     消息读记录
 */

@Data
@Entity
@Table(name="base_message_read")
public class MessageRead extends ModelBase{
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
     * 用户id
     */
    @Column(name="user_id")
    private String userId;

    /**
     * 阅读时间
     */
    @Column(name="read_dt")
    private Date readDt;

}
