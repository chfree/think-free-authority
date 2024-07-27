package com.cditer.free.devops.web.apis;

import com.cditer.free.dbdevops.data.model.TableSetting;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingSearch;
import com.cditer.free.dbdevops.data.viewmodel.TableSettingView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListKeyValView;
import com.cditer.free.dbdevops.data.viewmodel.dtview.DataListQueryResp;
import com.cditer.free.dbdevops.logical.service.ITableSettingService;
import com.cditer.free.security.baseapi.TokenApi;
import com.cditer.free.core.message.web.BasePagerReq;
import com.cditer.free.core.message.web.BasePagerResp;
import com.cditer.free.core.message.web.BaseRequest;
import com.cditer.free.core.message.web.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * @author      C.H
 * @email       chfree365@qq.com
 * @createtime  2024-06-14 12:38:14
 * @comment     表信息配置
 */
@RestController
@RequestMapping(value = "/api/v1/dbDevops/tableSetting/",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="表信息配置管理",value ="表信息配置相关的操作")
public class TableSettingApi extends TokenApi {
    @Autowired
    private ITableSettingService tableSettingService;

    @ApiOperation(value = "获取表信息配置列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid BasePagerReq<TableSettingSearch> req){
        int totalCount = tableSettingService.queryCountBySearch(req.getData());
        List<TableSettingView> list = tableSettingService.queryListViewBySearch(req.getData(), req.getPager());

        for (TableSettingView tableSetting : list) {
            tableSetting.setTableName(null);
        }

        return BasePagerResp.success(list, totalCount);
    }

    @ApiOperation(value = "获取指定表信息配置")
    @GetMapping("get")
    public BaseResponse<TableSettingView> get(@Valid @NotBlank(message = "表信息配置id不能为空") String id){
        TableSettingView tableSetting = tableSettingService.queryModelViewById(id);

        return BaseResponse.success(tableSetting);
    }

    @ApiOperation(value = "搜索表信息配置数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(@RequestBody BaseRequest<TableSettingSearch> req){
        int count =  tableSettingService.queryCountBySearch(req.getData());

        return BaseResponse.success(count);
    }

    @ApiOperation(value = "删除一个表信息配置")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "表信息配置id不能为空")String id){
        boolean result =  tableSettingService.deleteModel(id);

        return BaseResponse.success(result);
    }

    @ApiOperation(value = "保存一个表信息配置")
    @PostMapping("save")
    public BaseResponse save(@RequestBody @Valid BaseRequest<TableSetting> req){
        boolean result = tableSettingService.saveTableSetting(req.getData());

        return BaseResponse.success(result);
    }

    @PostMapping("queryDataList")
    @ApiOperation(value = "queryDataList")
    public BaseResponse<DataListQueryResp> queryDataList(@RequestBody BasePagerReq<DataListKeyValView> request){
        DataListQueryResp dataListQueryResp = tableSettingService.queryDataList(request.getData(), request.getPager());

        return BaseResponse.success(dataListQueryResp);
    }
}
