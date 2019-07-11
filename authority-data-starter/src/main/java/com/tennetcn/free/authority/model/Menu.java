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
@Table(name="base_authority_menu")
public class Menu  extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    @NonNull
    private String id;

    //名称
    @Column(name="name")
    @NonNull
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

    //url
    @Column(name="url")
    private String url;

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

    //按钮类型
    @Column(name="button_type")
    private String buttonType;

    //菜单标识
    @Column(name="menu_mark")
    private String menuMark;

    //排序字段
    @Column(name="sort_code")
    private Integer sortCode;

    //是否删除
    @Column(name="delete_mark")
    private Integer deleteMark;

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
}
