package com.cditer.free.devops.data.entity.apimodel.projectpuburl;

import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.devops.data.entity.viewmodel.ProjectPuburlSearch;
import lombok.Data;

/**
 * @author      auto build code by think
 * @email       chfree001@gmail.com
 * @createtime  2021-10-01 20:55:44
 * @comment     项目公共访问地址
 */

@Data
public class ProjectPuburlListReq extends BasePagerReq {
    private ProjectPuburlSearch search;
}