package com.tennetcn.free.authority.model;

import com.tennetcn.free.data.message.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Accessors
@Entity
@Table(name="base_authority_user_role")
public class UserRole extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //创建时间
    @Column(name="createDate")
    private Date createDate;

    //创建人id
    @Column(name="createUserId")
    private String createUserId;

    //创建人名称
    @Column(name="createUserName")
    private String createUserName;

    //用户id
    @Column(name="loginUserId")
    private String loginUserId;

    //角色id
    @Column(name="roleId")
    private String roleId;

    //是否删除
    @Column(name="deleteMark")
    private Integer deleteMark;
}
