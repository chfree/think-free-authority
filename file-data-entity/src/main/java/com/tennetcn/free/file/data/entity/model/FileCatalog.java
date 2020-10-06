package com.tennetcn.free.file.data.entity.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:11:38
 * @comment     文件目录表
 */

@Data
@Entity
@Table(name="base_file_catalog")
public class FileCatalog extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private String userId;

    /**
     * 名称
     */
    @Column(name="name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name="create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name="update_date")
    private Date updateDate;

    /**
     * 备注
     */
    @Column(name="comments")
    private String comments;

}