package com.cditer.free.develop.data.entity.model;

import com.cditer.free.core.message.data.ModelBase;
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
@Table(name = "base_develop_dict_table")
public class DictTable extends ModelBase {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "entity_name")
    private String entityName;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "sort_code")
    private int sortCode;

    @Column(name = "comments")
    private String comments;

    @Column(name = "dbname")
    private String dbname;

}
