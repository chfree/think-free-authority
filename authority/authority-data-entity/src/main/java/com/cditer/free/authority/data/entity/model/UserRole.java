package com.cditer.free.authority.data.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="base_authority_user_role")
@Builder
@AllArgsConstructor
public class UserRole extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //用户id
    @Column(name="user_id")
    private String userId;

    //角色id
    @Column(name="role_id")
    private String roleId;

    public UserRole() {

    }
}
