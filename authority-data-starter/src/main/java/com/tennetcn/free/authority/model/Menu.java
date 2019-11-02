package com.tennetcn.free.authority.model;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.enums.YesOrNoInteger;
import com.tennetcn.free.core.message.ModelBase;
import com.tennetcn.free.core.message.OrderByEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="base_authority_menu")
public class Menu  extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //名称
    @Column(name="name")
    private String name;

    //标题
    @Column(name="title")
    private String title;

    //图标
    @Column(name="icon")
    private String icon;

    //隐藏
    @Column(name="hidden")
    private Integer hidden;

    //path
    @Column(name="path")
    private String path;

    @Column(name="page_path")
    private String pagePath;

    //类型
    @Column(name="type")
    private String type;

    //使用类型标记
    @Column(name="use_type")
    private String useType;

    //父级id
    @Column(name="parent_id")
    private String parentId;

    //主题
    @Column(name="theme")
    private String theme;

    //菜单标识
    @Column(name="menu_mark")
    private String menuMark;

    //排序字段
    @OrderBy(OrderByEnum.ASC)
    @Column(name="sort_code")
    private Integer sortCode = 1;

    //级别
    @Column(name="level")
    private Integer level;

    //是否删除
    @Column(name="delete_mark")
    private Integer deleteMark = YesOrNoInteger.NO;

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
