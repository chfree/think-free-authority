package com.tennetcn.free.file.apis;

import com.tennetcn.free.file.data.entity.apimodel.filebsn.FileBsnListReq;
import com.tennetcn.free.file.data.entity.apimodel.filebsn.FileBsnListResp;
import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.file.data.entity.model.FileBsn;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnSearch;
import com.tennetcn.free.file.data.entity.viewmodel.FileBsnView;
import com.tennetcn.free.file.service.IFileBsnService;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-09-14 19:29
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/file/fileBsn/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件业务",value ="文件业务相关的操作")
public class FileBsnApi extends AuthorityApi {
    @Autowired
    IFileBsnService fileBsnService;

    @ApiOperation(value = "获取文件业务表列表")
    @PostMapping("list")
    public BaseResponse list(@RequestBody @Valid FileBsnListReq listReq){
        FileBsnListResp resp = new FileBsnListResp();
        resp.setTotalCount(fileBsnService.queryViewCountBySearch(listReq.getSearch()));
        resp.setFileBsnViews(fileBsnService.queryViewListBySearch(listReq.getSearch(),listReq.getPager()));

        return resp;
    }

    @ApiOperation(value = "获取指定文件业务")
    @GetMapping("get")
    public BaseResponse get(@Valid @NotBlank(message = "文件业务id不能为空") String id){
        BaseResponse response=new BaseResponse();

        FileBsnView fileBsnView = fileBsnService.queryViewModelById(id);
        response.put("fileBsn",fileBsnView);

        return response;
    }

    @ApiOperation(value = "搜索文件业务表数量")
    @PostMapping("countSearch")
    public BaseResponse countSearch(FileBsnSearch search){
        BaseResponse response=new BaseResponse();

        int count =  fileBsnService.queryCountBySearch(search);
        response.put("count",count);

        return response;
    }

    @ApiOperation(value = "删除一个文件业务表")
    @PostMapping("delete")
    public BaseResponse delete(@Valid @NotBlank(message = "文件业务表id不能为空")String id){
        BaseResponse response=new BaseResponse();

        boolean result =  fileBsnService.deleteModel(id);
        response.put("result",result);

        return response;
    }
}
