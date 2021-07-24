package com.cditer.free.file.data.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:19:13
 * @comment     文件信息表
 */

@Data
@Entity
@Table(name="base_file_info")
public class FileInfo extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="file_id")
    private String fileId;

    /**
     * 大小
     */
    @Column(name="size")
    private long size;

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
     * 删除标记
     */
    @Column(name="delete_mark")
    private String deleteMark;

    public String getFileName(){
        return this.fileId + "." + this.suffix;
    }

}