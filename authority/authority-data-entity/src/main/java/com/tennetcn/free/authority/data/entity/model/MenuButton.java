package com.tennetcn.free.authority.data.entity.model;

import com.tennetcn.free.core.enums.YesOrNoInteger;
import com.tennetcn.free.core.message.data.ModelBase;
import com.tennetcn.free.core.message.data.OrderByEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-10 23:54
 * @comment
 */

@Data
@Builder
@Entity
@Table(name="base_authority_menu_button")
public class MenuButton extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    private String id;

    //菜单id
    @Column(name="menu_id")
    private String menuId;

    //按钮名称
    @Column(name="name")
    private String name;

    //图标
    @Column(name="icon")
    private String icon;

    //主题
    @Column(name="theme")
    private String theme;

    //标题
    @Column(name="title")
    private String title;

    //按钮类型
    @Column(name="button_type")
    private String buttonType;

    //排序字段
    @OrderBy(value= OrderByEnum.ASC)
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

    //备注
    @Column(name="comments")
    private String comments;

}
