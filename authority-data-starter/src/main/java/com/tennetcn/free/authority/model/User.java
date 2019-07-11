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
@Table(name="base_authority_user")
public class User  extends ModelBase {
    @Id
    @Column(name="id")
    @NonNull
    private String id;

    @Column(name="name")
    @NonNull
    private String name;

    @Column(name="login_code")
    @NonNull
    private String loginCode;

    @Column(name="mark_code")
    private String markCode;

    @Column(name="sex")
    private String sex;

    @Column(name="theme")
    private String theme;

    @Column(name="mobile")
    private String mobile;

    @Column(name="email")
    private String email;

    @Column(name="delete_mark")
    private Integer deleteMark;

    @OrderBy(value= OrderByEnum.DESC)
    @Column(name="create_date")
    private Date createDate;

    @Column(name="create_user_id")
    private String createUserId;

    @Column(name="create_user_name")
    private String createUserName;

    @Column(name="modify_Date")
    private Date modifyDate;

    @Column(name="modify_user_id")
    private String modifyUserId;

    @Column(name="modify_user_name")
    private String modifyUserName;

    @Column(name="user_mark")
    private String userMark;

    @Column(name="business_id")
    private String businessId;
}
