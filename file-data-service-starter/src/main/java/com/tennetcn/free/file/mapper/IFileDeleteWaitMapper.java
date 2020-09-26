package com.tennetcn.free.file.mapper;

import com.tennetcn.free.core.message.data.PagerModel;
import com.tennetcn.free.data.dao.base.IMapper;
import com.tennetcn.free.file.data.entity.model.FileDeleteWait;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-21 12:10:54
 * @comment     文件删除等待表
 */

@Mapper
public interface IFileDeleteWaitMapper extends IMapper<FileDeleteWait>{
    List<FileDeleteWait> queryCanDeleteFile(@Param("pager")PagerModel pager);
}