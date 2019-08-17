package com.tennetcn.free.authority.model;

import cn.hutool.core.date.DateUtil;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.data.enums.YesOrNoInteger;
import com.tennetcn.free.data.message.ModelBase;
import com.tennetcn.free.data.message.OrderByEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-10 23:54
 * @comment
 */

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="base_authority_button")
public class Button extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    @NonNull
    private String id;

    //事件名称
    @Column(name="event_name")
    private String eventName;

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
