package com.cditer.free.authority.data.entity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-05-31 12:45:57
 * @comment     权限组
 */

@Data
@Entity
@Table(name="base_authority_group")
public class Group extends ModelBase{
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
     * 描述
     */
    @Column(name="description")
    private String description;

    /**
     * 逻辑标记
     */
    @Column(name="group_mark")
    private String groupMark;

    /**
     * 级别
     */
    @Column(name="level")
    private String level;

    /**
     * 状态
     */
    @Column(name="status")
    private String status;

    /**
     * 排序字段
     */
    @Column(name="sort_code")
    private Integer sortCode;

    /**
     * 创建时间
     */
    @Column(name="create_date")
    private Date createDate;

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

    /**
     * 修改时间
     */
    @Column(name="modify_date")
    private Date modifyDate;

    /**
     * 修改人id
     */
    @Column(name="modify_user_id")
    private String modifyUserId;

    /**
     * 修改人名称
     */
    @Column(name="modify_user_name")
    private String modifyUserName;

}
