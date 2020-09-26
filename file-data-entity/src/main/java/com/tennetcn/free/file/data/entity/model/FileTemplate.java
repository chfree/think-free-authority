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
 * @createtime  2020-09-14 23:28:14
 * @comment     文件模板表
 */

@Data
@Entity
@Table(name="base_file_template")
public class FileTemplate extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 文件id
     */
    @Column(name="file_id")
    private String fileId;

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
     * 显示名称
     */
    @Column(name="display_name")
    private String displayName;

    /**
     * 上传时间
     */
    @Column(name="upload_date")
    private Date uploadDate;

    /**
     * 上传人
     */
    @Column(name="upload_user_id")
    private String uploadUserId;

    /**
     * 上传人名称
     */
    @Column(name="upload_user_name")
    private String uploadUserName;

    /**
     * 备注
     */
    @Column(name="comments")
    private String comments;
}