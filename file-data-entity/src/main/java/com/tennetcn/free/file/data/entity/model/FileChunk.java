package com.tennetcn.free.file.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.tennetcn.free.core.message.data.ModelBase;

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
    @Column(name="total_chunk")
    private Integer totalChunk;

    /**
     * 文件类型
     */
    @Column(name="type")
    private String type;

    /**
     * 业务id
     */
    @Column(name="bsn_id")
    private String bsnId;

}