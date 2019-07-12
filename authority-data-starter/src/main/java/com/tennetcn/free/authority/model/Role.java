package com.tennetcn.free.authority.model;

import com.tennetcn.free.data.message.ModelBase;
import com.tennetcn.free.data.message.OrderByEnum;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Accessors
@Entity
@Table(name="base_authority_role")
public class Role extends ModelBase {
    //角色id
    @Id
    @Column(name="roleId")
    @NonNull
    private String roleId;

    //角色名称
    @Column(name="roleName")
    @NonNull
    private String roleName;

    //角色与逻辑代码的标记
    @Column(name="roleMark")
    private String roleMark;

    //说明
    @Column(name="description")
    private String description;

    //级别
    @Column(name="level")
    private Integer level;

    //父代id
    @Column(name="parentId")
    private String parentId;


    //是否删除
    @Column(name="deleteMark")
    private Integer deleteMark;

    //标记
    @Column(name="markCode")
    private String markCode;

    //排序字段
    @OrderBy(value= OrderByEnum.ASC)
    @Column(name="sortCode")
    private Integer sortCode;

    //创建时间
    @Column(name="createDate")
    private Date createDate;

    //创建人id
    @Column(name="createUserId")
    private String createUserId;

    //创建人名称
    @Column(name="createUserName")
    private String createUserName;

    //修改时间
    @Column(name="modifyDate")
    private Date modifyDate;

    //修改人id
    @Column(name="modifyUserId")
    private String modifyUserId;

    //修改人名称
    @Column(name="modifyUserName")
    private String modifyUserName;

    //备注
    @Column(name="comments")
    private String comments;

    //公司id
    @Column(name="buId")
    private String buId;
}
