package com.tennetcn.free.authority.data.entity.model;

import com.tennetcn.free.core.message.data.ModelBase;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name="base_authority_user_role")
public class UserRole extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //创建时间
    @Column(name="create_date")
    private Date createDate;

    //创建人id
    @Column(name="create_user_id")
    private String createUserId;

    //创建人名称
    @Column(name="create_user_name")
    private String createUserName;

    //用户id
    @Column(name="user_id")
    private String userId;

    //角色id
    @Column(name="role_id")
    private String roleId;

    //是否删除
    @Column(name="delete_mark")
    private Integer deleteMark;
}
