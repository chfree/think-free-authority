package com.tennetcn.free.file.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

import java.util.Date;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-11-22 19:25:35
 * @comment     文件块
 */

@Data
@Entity
@Table(name="base_file_chunk")
public class FileChunk extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 当前文件块
     * 从1开始
     */
    @Column(name="chunk_number")
    private Integer chunkNumber;

    /**
     * 分块大小
     */
    @Column(name="chunk_size")
    private Long chunkSize;

    /**
     * 当前分块大小
     */
    @Column(name="current_chunk_size")
    private Long currentChunkSize;

    /**
     * 总大小
     */
    @Column(name="total_size")
    private Long totalSize;

    /**
     * 文件标识
     * sha1
     */
    @Column(name="identifier")
    private String identifier;

    /**
     * 文件名
     */
    @Column(name="filename")
    private String filename;

    /**
     * 总块数
     */
    @Column(name="total_chunks")
    private Integer totalChunks;

    /**
     * 文件类型
     */
    @Column(name="mime_type")
    private String mimeType;

    /**
     * 文件后缀
     */
    @Column(name="suffix")
    private String suffix;

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
     * 状态
     */
    @Column(name="status")
    private String status;

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
     * 路径
     */
    @Column(name="path")
    private String path;


}