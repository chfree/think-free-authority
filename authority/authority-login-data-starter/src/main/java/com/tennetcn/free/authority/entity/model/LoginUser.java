package com.tennetcn.free.authority.entity.model;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.enums.YesOrNoInteger;
import com.tennetcn.free.core.message.data.ModelBase;
import com.tennetcn.free.core.message.data.OrderByEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Alias("loginUser")
@Table(name="base_authority_user")
public class LoginUser extends ModelBase {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="account")
    private String account;

    @JsonIgnore
    @Column(name="password")
    private String password;

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
    private Integer deleteMark = YesOrNoInteger.NO;

    @OrderBy(value= OrderByEnum.DESC)
    @Column(name="create_date")
    private Date createDate;

    @Column(name="create_user_id")
    private String createUserId;

    @Column(name="create_user_name")
    private String createUserName;

    @Column(name="modify_date")
    private Date modifyDate;

    @Column(name="modify_user_id")
    private String modifyUserId;

    @Column(name="modify_user_name")
    private String modifyUserName;

    @Column(name="user_mark")
    private String userMark;

    @Column(name="unique_mark")
    private String uniqueMark;

    @Column(name="status")
    private String status;

    @Column(name="department_id")
    private String departmentId;

    @Override
    public void setModelStatus(ModelStatus modelStatus) {
        super.setModelStatus(modelStatus);

        if(modelStatus==ModelStatus.add){
            setCreateDate(DateUtil.date());
        }else if(modelStatus==ModelStatus.update){
            setModifyDate(DateUtil.date());
        }
    }

}
