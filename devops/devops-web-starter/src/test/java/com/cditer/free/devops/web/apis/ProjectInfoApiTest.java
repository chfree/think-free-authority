package com.cditer.free.devops.web.apis;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.coreweb.http.entity.RequestEntityEx;
import com.cditer.free.coreweb.http.entity.RequestEntityExBuilder;
import com.cditer.free.coreweb.http.entity.ResponseEntityEx;
import com.cditer.free.coreweb.http.request.IServerRequestHelper;
import com.cditer.free.devops.BaseTest;
import com.cditer.free.devops.TestContant;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListReq;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListResp;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import com.cditer.free.jwt.core.ITokenCreate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ProjectInfoApiTest extends BaseTest {

    @Autowired
    IServerRequestHelper serverRequestHelper;

    @Autowired
    ITokenCreate tokenCreate;

    private String getToken(){
        return tokenCreate.createToken("001", "cheng", "CH");
    }

    private final static String listUrl = TestContant.hostPrefix + "/devops/projectInfo/list";

    @Test
    public void list() {
        ProjectInfoListReq req = new ProjectInfoListReq();
        ProjectInfoSearch search = new ProjectInfoSearch();
        req.setSearch(search);

        req.setPager(new PagerModel(10, 1));

        RequestEntityEx indexRquest = RequestEntityExBuilder.builder(ProjectInfoListReq.class)
                .setMethod(HttpMethod.POST)
                .addHeader("Authorization", getToken())
                .setBody(req)
                .setUrl(listUrl).build();

        ResponseEntityEx<ProjectInfoListResp> resp = serverRequestHelper.request(indexRquest, ProjectInfoListResp.class);
        List<ProjectInfo> projectInfos = resp.getBody().getProjectInfos();

        Assert.assertTrue(CollectionUtils.isEmpty(projectInfos));
    }

    @Test
    public void get() {
    }

    @Test
    public void countSearch() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}