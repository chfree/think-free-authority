package com.tennetcn.free.authority.model;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.data.enums.YesOrNoInteger;
import com.tennetcn.free.data.message.ModelBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-10 23:55
 * @comment
 */

@Data
@Entity
@Table(name="base_authority_department")
public class Department extends ModelBase {
    //部门id
    @Id
    @Column(name="id")
    private String id;

    //部门全称
    @Column(name="full_name")
    private String fullName;

    //部门简称
    @Column(name="short_name")
    private String shortName;

    //部门类型
    @Column(name="department_type")
    private String departmentType;

    //部门编码
    @Column(name="depart_code")
    private String departCode;

    //部门编号
    @Column(name="depart_num")
    private String departNum;

    //地址
    @Column(name="address")
    private String address;

    //所属区域
    @Column(name="belong_area")
    private String belongArea;

    //内部联系方式
    @Column(name="inner_phone")
    private String innerPhone;

    //外部联系方式
    @Column(name="outer_phone")
    private String outerPhone;

    //部门标志
    @Column(name="department_flag")
    private Integer departmentFlag;

    //描述
    @Column(name="description")
    private String description;

    //层级
    @Column(name="hierarchy_code")
    private String hierarchyCode;

    //级别
    @Column(name="level")
    private Integer level;

    //标记
    @Column(name="mark_code")
    private String markCode;

    //父级id
    @Column(name="parent_id")
    private String parentId;

    //排序
    @Column(name="sort_code")
    private Integer sortCode = 1;

    //是否删除
    @Column(name="delete_mark")
    private Integer deleteMark = YesOrNoInteger.NO;

    //创建日期
    @Column(name="create_date")
    private Date createDate;

    //创建者Id
    @Column(name="create_user_id")
    private String createUserId;

    //创建者名称
    @Column(name="create_user_name")
    private String createUserName;

    //修改日期
    @Column(name="modify_date")
    private Date modifyDate;

    //修改者Id
    @Column(name="modify_user_id")
    private String modifyUserId;

    //修改者名称
    @Column(name="modify_user_name")
    private String modifyUserName;

    //备注
    @Column(name="comments")
    private String comments;

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
