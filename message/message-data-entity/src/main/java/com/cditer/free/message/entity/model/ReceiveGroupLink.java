package com.cditer.free.message.entity.model;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2022-06-28 12:39:14
 * @comment     消息组连接
 */

@Data
@Entity
@Table(name="base_message_group_link")
public class ReceiveGroupLink extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 组id
     */
    @Column(name="group_id")
    private String groupId;

    /**
     * 连接类型
     */
    @Column(name="link_type")
    private String linkType;

    /**
     * 连接id
     */
    @Column(name="link_id")
    private String linkId;

}