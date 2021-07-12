package com.tennetcn.free.develop.apis;

import cn.hutool.core.util.IdUtil;
import com.tennetcn.free.core.enums.ModelStatus;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.develop.data.entity.apimodel.dicttable.DictTableListReq;
import com.tennetcn.free.develop.data.entity.apimodel.dicttable.DictTableListResp;
import com.tennetcn.free.develop.data.entity.apimodel.dicttable.SaveDictTableReq;
import com.tennetcn.free.develop.data.entity.model.DictTable;
import com.tennetcn.free.develop.service.IDictTableService;
import com.tennetcn.free.develop.data.entity.viewmodel.DictTableSearch;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/v1/develop/dictTable/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="字典表管理",value ="字典表相关的操作")
public class DictTableApi extends AuthorityApi {

    @Autowired
    IDictTableService dictTableService;

    @ApiOperation(value = "获取字典表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid DictTableListReq listReq){
        DictTableListResp resp = new DictTableListResp();
        resp.setTotalCount(dictTableService.queryCountBySearch(listReq.getSearch()));
        resp.setDictTables(dictTableService.queryListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定字典表")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "字典表id不能为空") String id){
        BaseResponse response=new BaseResponse();

        DictTable dictTable = dictTableService.queryModel(id);
        response.put("dictTable",dictTable);

        return response;
    }

    @ApiOperation(value = "搜索字典表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(DictTableSearch search){
        BaseResponse response=new BaseResponse();

        int count =  dictTableService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除指定字典表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid String id){
        return null;
    }

    @ApiOperation(value = "保存一个字典表")
    @PostMapping("save")
    public BaseResponse save(@Valid SaveDictTableReq saveDictTableReq){
        BaseResponse response = new BaseResponse();
        if(StringUtils.isEmpty(saveDictTableReq.getId())){
            saveDictTableReq.setId(IdUtil.randomUUID());
            saveDictTableReq.setModelStatus(ModelStatus.add);
        }else{
            saveDictTableReq.setModelStatus(ModelStatus.update);
        }

        boolean result = dictTableService.applyChange(saveDictTableReq);
        response.put("result",result);

        return response;
    }
}
