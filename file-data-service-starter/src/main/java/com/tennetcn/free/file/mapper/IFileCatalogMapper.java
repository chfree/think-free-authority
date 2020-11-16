package com.tennetcn.free.file.mapper;

import com.tennetcn.free.file.data.entity.model.FileCatalog;
import org.apache.ibatis.annotations.Mapper;
import com.tennetcn.free.data.dao.base.IMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-10-06 22:18:29
 * @comment     文件目录表
 */

@Mapper
public interface IFileCatalogMapper extends IMapper<FileCatalog>{
    List<FileCatalog>  queryChildList(@Param("userId") String userId, @Param("catalogId") String catalogId);
}