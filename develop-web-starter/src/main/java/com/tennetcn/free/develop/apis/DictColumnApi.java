package com.tennetcn.free.develop.apis;

import cn.hutool.core.util.IdUtil;
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
@RequestMapping(value = "/api/v1/authority/business/")
@Api(tags="字典字段管理",value ="字典字段相关的操作")
public class DictColumnApi extends AuthorityApi {

    @Autowired
    IDictColumnService dictColumnService;

    @ApiOperation(value = "获取字典字段列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid DictColumnListReq listReq){
        DictColumnListResp resp = new DictColumnListResp();
        resp.setTotalCount(dictColumnService.queryCountBySearch(listReq.getSearch()));
        resp.setDictColumns(dictColumnService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定字典字段")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "字典字段id不能为空") String id){
        BaseResponse response=new BaseResponse();

        DictColumn dictColumn = dictColumnService.queryModel(id);
        response.put("dictColumn",dictColumn);

        return response;
    }

    @ApiOperation(value = "搜索字典字段数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(DictColumnSearch search){
        BaseResponse response=new BaseResponse();

        int count =  dictColumnService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定字典字段")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个字典字段")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveDictColumnReq saveDictColumnReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveDictColumnReq.getId())){
            saveDictColumnReq.setId(IdUtil.randomUUID());
            saveDictColumnReq.setModelStatus(ModelStatus.add);
        }else{
            saveDictColumnReq.setModelStatus(ModelStatus.update);
        }

        boolean result = dictColumnService.applyChange(saveDictColumnReq);
        response.put("result",result);

        return response;
    }
}
