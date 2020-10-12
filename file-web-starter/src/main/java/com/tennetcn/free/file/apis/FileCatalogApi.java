package com.tennetcn.free.file.apis;

import com.tennetcn.free.core.message.web.BaseResponse;
import com.tennetcn.free.file.data.entity.model.FileCatalog;
import com.tennetcn.free.file.data.entity.viewmodel.FileCatalogTree;
import com.tennetcn.free.file.service.IFileCatalogService;
import com.tennetcn.free.security.webapi.AuthorityApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2020-10-09 12:27
 * @comment
 */

@RestController
@RequestMapping(value = "/api/v1/file/fileCatalog/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="文件目录",value ="文件目录相关的操作")
public class FileCatalogApi extends AuthorityApi {

    @Autowired
    IFileCatalogService catalogService;

    @ApiOperation(value = "获取文件目录树")
    @PostMapping("ownListTree")
    public BaseResponse ownListTree(String userId){
        BaseResponse resp = new BaseResponse();
        if(StringUtils.isEmpty(userId)){
            userId = getLoginId();
        }
        List<FileCatalogTree> fileCatalogTrees = catalogService.queryListByOwnShow(userId);
        resp.put("fileCatalogTrees",fileCatalogTrees);

        return resp;
    }

    @ApiOperation(value = "获取当前节点获得向上的路径信息")
    @PostMapping("getPathCatalog")
    public BaseResponse getPathCatalog(@Valid @NotEmpty(message = "id不能为空") String id){
        BaseResponse resp = new BaseResponse();

        List<FileCatalog> fileCatalogs = catalogService.queryPathList(id);
        resp.put("fileCatalogs", fileCatalogs);

        return resp;
    }

    @ApiOperation(value = "获取当前节点子一级信息")
    @PostMapping("getChildCatalog")
    public BaseResponse getChildCatalog(@Valid @NotEmpty(message = "id不能为空") String id){
        BaseResponse resp = new BaseResponse();

        List<FileCatalog> fileCatalogs = catalogService.queryChildList(id);
        resp.put("fileCatalogs", fileCatalogs);

        return resp;
    }
}
