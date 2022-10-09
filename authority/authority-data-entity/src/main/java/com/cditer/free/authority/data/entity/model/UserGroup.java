package com.cditer.free.authority.data.entity.model;

import com.cditer.free.core.message.data.ModelBase;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="base_authority_user_group")
public class UserGroup extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //用户id
    @Column(name="user_id")
    private String userId;

    //用户组id
    @Column(name="group_id")
    private String groupId;
}
