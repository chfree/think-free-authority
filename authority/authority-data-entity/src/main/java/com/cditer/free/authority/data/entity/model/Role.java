package com.cditer.free.authority.data.entity.model;

import cn.hutool.core.date.DateUtil;
import com.cditer.free.core.enums.ModelStatus;
import com.cditer.free.core.enums.YesOrNoInteger;
import com.cditer.free.core.message.data.ModelBase;
import com.cditer.free.core.message.data.OrderByEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="base_authority_role")
public class Role extends ModelBase {
    //角色id
    @Id
    @Column(name="id")
    private String id;

    //角色名称
    @Column(name="role_name")
    private String roleName;

    //角色与逻辑代码的标记
    @Column(name="role_mark")
    private String roleMark;

    //说明
    @Column(name="description")
    private String description;

    //级别
    @Column(name="level")
    private Integer level = 1;

    //是否删除
    @Column(name="delete_mark")
    private Integer deleteMark = YesOrNoInteger.NO;

    //标记
    @Column(name="mark_code")
    private String markCode;

    //排序字段
    @OrderBy(value= OrderByEnum.ASC)
    @Column(name="sort_code")
    private Integer sortCode = 1;

    //创建时间
    @Column(name="create_date")
    private Date createDate;

    //创建人id
    @Column(name="create_user_id")
    private String createUserId;

    //创建人名称
    @Column(name="create_user_name")
    private String createUserName;

    //修改时间
    @Column(name="modify_date")
    private Date modifyDate;

    //修改人id
    @Column(name="modify_user_id")
    private String modifyUserId;

    //修改人名称
    @Column(name="modify_user_name")
    private String modifyUserName;

    //备注
    @Column(name="comments")
    private String comments;

    //公司id
    @Column(name="bu_id")
    private String buId;

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
