package com.tennetcn.free.authority.data.entity.apimodel.filebsn;

import com.tennetcn.free.authority.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.core.message.web.BasePagerReq;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 21:50:25
 * @comment     文件业务表
 */

@Data
public class FileBsnListReq extends BasePagerReq {
    private FileBsnSearch search;
}