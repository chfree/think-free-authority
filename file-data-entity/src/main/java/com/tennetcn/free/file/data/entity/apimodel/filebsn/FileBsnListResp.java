package com.tennetcn.free.file.data.entity.apimodel.filebsn;

import com.tennetcn.free.core.message.web.BasePagerResp;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;
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