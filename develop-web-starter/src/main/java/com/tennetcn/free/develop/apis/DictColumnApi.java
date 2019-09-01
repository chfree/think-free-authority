package com.tennetcn.free.develop.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.core.message.PagerModel;
import com.tennetcn.free.data.enums.ModelStatus;
import com.tennetcn.free.develop.apimodel.dictcolumn.DictColumnListReq;
import com.tennetcn.free.develop.apimodel.dictcolumn.DictColumnListResp;
import com.tennetcn.free.develop.apimodel.dictcolumn.SaveDictColumnReq;
import com.tennetcn.free.develop.model.DictColumn;
import com.tennetcn.free.develop.service.IDictColumnService;
import com.tennetcn.free.develop.viewmodel.DictColumnSearch;
import com.tennetcn.free.security.webapi.AuthorityApi;
import com.tennetcn.free.web.webapi.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2019-09-01 09:11
 * @comment
 */
@RestController
@RequestMapping(value = "/api/v1/develop/dictColumn/")
@Api(tags="字典字段管理",value ="字典字段相关的操作")
public class DictColumnApi extends AuthorityApi {

    @Autowired
    IDictColumnService dictColumnService;

    @ApiOperation(value = "获取字典字段列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid DictColumnListReq listReq){
        DictColumnListResp resp = new DictColumnListResp();
        PagerModel pagerModel =new PagerModel(200,1);

        resp.setDictColumns(dictColumnService.queryListBySearch(listReq.getSearch(),pagerModel));

        return resp;
    }

    @ApiOperation(value = "保存一个字典字段")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid SaveDictColumnReq saveDictColumnReq){
        BaseResponse response = new BaseResponse();

        response.put("result",dictColumnService.saveDictColumns(saveDictColumnReq.getTableId(),saveDictColumnReq.getDictColumns()));

        return response;
    }
}
