package com.tennetcn.free.develop.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-02-05 16:04:51
 * @comment     代码模板
 */

@Data
@Entity
@Table(name="base_develop_code_tmp")
public class CodeTmp extends ModelBase{
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
     * 模板描述
     */
    @Column(name="comment")
    private String comment;

    /**
     * 模板内容
     */
    @Column(name="content")
    private String content;

    /**
     * 是否公开
     */
    @Column(name="pub")
    private String pub;

    /**
     * 类型
     */
    @Column(name="type")
    private String type;

    /**
     * 创建人id
     */
    @Column(name="create_user_id")
    private String createUserId;

    /**
     * 创建人名称
     */
    @Column(name="create_user_name")
    private String createUserName;
}