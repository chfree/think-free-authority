package com.tennetcn.free.authority.data.model;

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
@Table(name="base_authority_role_func")
public class RoleFunc extends ModelBase {
    @Id
    @Column(name="id")
    private String id;

    //功能id
    @Column(name="func_id")
    private String funcId;

    //功能类型
    @Column(name="func_type")
    private String funcType;

    //角色Id
    @Column(name="role_id")
    private String roleId;

    //创建时间
    @Column(name="create_date")
    private Date createDate = DateUtil.date();

    //创建者id
    @Column(name="create_user_id")
    private String createUserId;

    //创建者名称
    @Column(name="create_user_name")
    private String createUserName;
}
