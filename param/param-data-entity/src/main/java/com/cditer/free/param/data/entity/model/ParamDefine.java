package com.cditer.free.param.data.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-27 22:42
 * @comment
 */

@Data
@Entity
@Table(name="base_param_define")
public class ParamDefine extends ModelBase {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="title")
    private String title;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="comments")
    private String comments;


}
