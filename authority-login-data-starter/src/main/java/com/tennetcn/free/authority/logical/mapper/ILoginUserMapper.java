package com.tennetcn.free.authority.logical.mapper;

import com.tennetcn.free.authority.entity.model.LoginUser;
import com.tennetcn.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 00:14
 * @comment
 */

@Mapper
public interface ILoginUserMapper extends IMapper<LoginUser> {
}
