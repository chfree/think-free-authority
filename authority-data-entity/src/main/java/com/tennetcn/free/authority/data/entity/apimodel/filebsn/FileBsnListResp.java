package com.tennetcn.free.authority.data.entity.apimodel.filebsn;

import com.tennetcn.free.authority.data.entity.model.FileBsn;
import com.tennetcn.free.authority.data.entity.viewmodel.FileBsnView;
import com.tennetcn.free.core.message.web.BasePagerResp;
import lombok.Data;
import java.util.List;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2020-09-14 21:52:44
 * @comment     文件业务表
 */

@Data
public class FileBsnListResp extends BasePagerResp {
    private List<FileBsnView> fileBsnViews;
}