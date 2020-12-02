package com.tennetcn.free.file.mapper;

import com.tennetcn.free.data.dao.base.IMapper;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-08-26 13:27:48
 * @comment     文件业务表
 */

@Mapper
public interface IFileBsnMapper extends IMapper<FileBsn>{
    List<String> queryOneLinkFileId(@Param("fileIds") List<String> fileIds);

    Integer queryNextSeqIndex(@Param("bsnId") String bsnId);
}