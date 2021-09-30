package com.cditer.free.devops.data.entity.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import lombok.Data;
import com.cditer.free.core.message.data.ModelBase;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-09-30 10:57:57
 * @comment     项目公共访问地址
 */

@Data
@Entity
@Table(name="dps_project_puburl")
public class ProjectPuburl extends ModelBase{
    /**
     * 主键
     */
    @Id
    @Column(name="id")
    private String id;

    /**
     * 项目id
     */
    @Column(name="project_id")
    private String projectId;

    /**
     * 访问地址
     */
    @Column(name="url")
    private String url;

    /**
     * 访问说明
     */
    @Column(name="descr")
    private String descr;

    /**
     * 访问标记
     */
    @Column(name="url_mark")
    private String urlMark;

}