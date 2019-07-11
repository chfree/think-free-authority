package com.tennetcn.free.authority.model;

import com.tennetcn.free.data.message.ModelBase;
import lombok.*;
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
@Table(name="base_authority_role_menu")
public class RoleMenu extends ModelBase {
    @Id
    @Column(name="id")
    @NonNull
    private String id;

    //菜单id
    @NonNull
    @Column(name="menu_id")
    private String menuId;

    //角色Id
    @NonNull
    @Column(name="role_id")
    private String roleId;

    //创建时间
    @Column(name="create_date")
    private Date createDate;

    //创建者id
    @Column(name="create_user_id")
    private String createUserId;

    //创建者名称
    @Column(name="create_user_name")
    private String createUserName;
}
