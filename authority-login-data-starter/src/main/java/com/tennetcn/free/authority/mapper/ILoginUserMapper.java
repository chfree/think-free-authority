package com.tennetcn.free.authority.mapper;

import com.tennetcn.free.authority.model.LoginUser;
import com.tennetcn.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-07-11 00:14
 * @comment
 */

@Mapper
public interface ILoginUserMapper extends IMapper<LoginUser> {
}
