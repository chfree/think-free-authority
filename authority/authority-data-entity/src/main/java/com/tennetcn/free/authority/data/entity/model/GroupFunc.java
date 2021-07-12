package com.tennetcn.free.authority.data.entity.model;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.core.message.data.ModelBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="base_authority_group_func")
public class GroupFunc extends ModelBase {
    @Id
    @Column(name="id")
    private String id;

    //功能id
    @Column(name="func_id")
    private String funcId;

    //功能类型
    @Column(name="func_type")
    private String funcType;

    //组Id
    @Column(name="group_id")
    private String groupId;
}
