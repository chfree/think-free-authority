package com.cditer.free.file.data.entity.model;

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
 * @createtime  2020-08-26 13:24:27
 * @comment     文件业务表
 */

@Data
@Entity
@Table(name="base_file_bsn")
public class FileBsn extends ModelBase{
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
     * 显示名称
     * sha1情况下，可能文件名不同，业务表存储当前上传的文件名称
     */
    @Column(name="display_name")
    private String displayName;

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
     * 删除标记
     */
    @Column(name="delete_mark")
    private String deleteMark;

    /**
     * 顺序索引，重名时候的记录
     */
    @Column(name="seq_index")
    private Integer seqIndex;
}