package com.tennetcn.free.authority.model;

import com.tennetcn.free.data.enums.YesOrNoInteger;
import com.tennetcn.free.data.message.ModelBase;
import com.tennetcn.free.data.message.OrderByEnum;
import lombok.*;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.ColumnType;

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
@Accessors
@Entity
@Table(name="base_authority_business")
public class Business extends ModelBase {
    //主键
    @Id
    @Column(name="id")
    @NonNull
    private String id;

    //商业单位名称
    @Column(name="name")
    @NonNull
    private String name;

    //性质
    @Column(name="nature")
    private String nature;

    //简称
    @ColumnType(column="short_name")
    private String shortName;

    //编码
    @Column(name="code")
    private String code;

    //助记码
    @Column(name="mark_code")
    private String markCode;

    //地址
    @Column(name="address")
    private String address;

    //电话
    @Column(name="phone")
    private String phone;

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
}
