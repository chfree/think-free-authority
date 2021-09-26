package com.cditer.free.login.service.logical.mapper;

import com.cditer.free.data.dao.base.IMapper;
import com.cditer.free.login.service.logical.entity.model.LoginUser;
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
    List<LoginUser> queryListMPByIds(List<String> ids);

    String getLoginUserNamesByIds(List<String> ids);
}
