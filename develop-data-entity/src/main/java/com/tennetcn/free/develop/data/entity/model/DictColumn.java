package com.tennetcn.free.develop.data.entity.model;

import com.tennetcn.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-08-31 22:34
 * @comment
 */

@Data
@Entity
@Table(name = "base_develop_dict_column")
public class DictColumn extends ModelBase {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "data_length")
    private String dataLength;

    @Column(name = "not_null")
    private String notNull;

    @Column(name = "is_key")
    private String isKey;

    @Column(name = "radix")
    private String radix;

    @Column(name = "formatter")
    private String formatter;

    @Column(name = "table_id")
    private String tableId;

    @Column(name = "sort_code")
    private int sortCode;

    @Column(name = "comments")
    private String comments;

}
