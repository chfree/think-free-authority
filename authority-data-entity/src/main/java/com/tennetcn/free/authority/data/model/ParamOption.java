package com.tennetcn.free.authority.data.model;

import com.tennetcn.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-27 22:42
 * @comment
 */

@Data
@Entity
@Table(name="base_param_option")
public class ParamOption extends ModelBase {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="text")
    private String text;

    @Column(name="value")
    private String value;

    @Column(name="define_id")
    private String defineId;

    @Column(name="sort_code")
    private int sortCode;

    @Column(name="status")
    private String status;

    @Column(name="comments")
    private String comments;
}
