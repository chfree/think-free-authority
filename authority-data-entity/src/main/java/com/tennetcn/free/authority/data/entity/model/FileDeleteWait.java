package com.tennetcn.free.authority.data.entity.model;

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
 * @createtime  2020-09-21 11:53:56
 * @comment     文件删除等待表
 */

@Data
@Entity
@Table(name="base_file_delete_wait")
public class FileDeleteWait extends ModelBase{
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
     * 大小
     */
    @Column(name="size")
    private Integer size;

    /**
     * 文件类型
     */
    @Column(name="mime_type")
    private String mimeType;

    /**
     * 文件的sha1
     */
    @Column(name="sha1")
    private String sha1;

    /**
     * 存储方式
     */
    @Column(name="store_type")
    private String storeType;

    /**
     * 后缀
     */
    @Column(name="suffix")
    private String suffix;

    /**
     * 路径
     */
    @Column(name="path")
    private String path;

    /**
     * 业务id
     */
    @Column(name="bsn_id")
    private String bsnId;

    /**
     * 业务类型
     */
    @Column(name="bsn_type")
    private String bsnType;

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
     * 上传时间
     */
    @Column(name="upload_date")
    private Date uploadDate;

    /**
     * 显示名称
     */
    @Column(name="display_name")
    private String displayName;

    /**
     * 添加时间
     */
    @Column(name="add_date")
    private Date addDate;

    /**
     * 等待时间
     */
    @Column(name="wait_day")
    private Integer waitDay;

}