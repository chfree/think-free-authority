package com.cditer.free.devops.web.apis;


import com.cditer.free.core.message.data.PagerModel;
import com.cditer.free.core.util.CommonUtils;
import com.cditer.free.coreweb.http.entity.RequestEntityEx;
import com.cditer.free.coreweb.http.entity.RequestEntityExBuilder;
import com.cditer.free.coreweb.http.entity.ResponseEntityEx;
import com.cditer.free.coreweb.http.request.IServerRequestHelper;
import com.cditer.free.data.dao.base.ISqlExpression;
import com.cditer.free.data.utils.SqlExpressionFactory;
import com.cditer.free.devops.BaseTest;
import com.cditer.free.devops.TestContant;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListReq;
import com.cditer.free.devops.data.entity.apimodel.projectinfo.ProjectInfoListResp;
import com.cditer.free.devops.data.entity.model.ProjectInfo;
import com.cditer.free.devops.data.entity.viewmodel.ProjectInfoSearch;
import com.cditer.free.devops.logical.dao.IProjectInfoDao;
import com.cditer.free.devops.logical.service.IProjectInfoService;
import com.cditer.free.jwt.core.ITokenCreate;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfoApiTest extends BaseTest {

    @Autowired
    IServerRequestHelper serverRequestHelper;

    @Autowired
    ITokenCreate tokenCreate;

    private String getToken() {
        return tokenCreate.createToken("001", "cheng", "CH");
    }

    private final static String listUrl = TestContant.hostPrefix + "/devops/projectInfo/list";

    @Autowired
    IProjectInfoService projectInfoService;

    @Autowired
    IProjectInfoDao projectInfoDao;

    @Before
    public void addTestData() {
        List<ProjectInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MockConfig config = new MockConfig().sizeRange(10, 20);
            ProjectInfo projectInfo = JMockData.mock(ProjectInfo.class, config);
            projectInfo.setId("TEST_" + CommonUtils.getUUID());
            projectInfo.setProjectNo("A" + CommonUtils.repairZero(i, 5));
            if(i==0) {
                projectInfo.setName("Think-Luna");
                projectInfo.setPort(8888);
            }

            list.add(projectInfo);
        }
        projectInfoService.insertListEx(list);
    }

    @After
    public void deleteTestData(){
        ISqlExpression sqlExpression = SqlExpressionFactory.createExpression();
        sqlExpression.delete().from(ProjectInfo.class).andRightLike(ProjectInfo::getId, "TEST_");

        projectInfoDao.delete(sqlExpression);
    }

    private ProjectInfoListResp buildListReqForSend(ProjectInfoSearch search){
        ProjectInfoListReq req = new ProjectInfoListReq();
        req.setSearch(search);

        req.setPager(new PagerModel(10, 1));

        RequestEntityEx listRquest = RequestEntityExBuilder.builder(ProjectInfoListReq.class)
                .setMethod(HttpMethod.POST)
                .addHeader("Authorization", getToken())
                .setBody(req)
                .setUrl(listUrl).build();

        ResponseEntityEx<ProjectInfoListResp> resp = serverRequestHelper.request(listRquest, ProjectInfoListResp.class);

        return resp.getBody();
    }

    @Test
    public void list() {
        ProjectInfoSearch search = new ProjectInfoSearch();

        ProjectInfoListResp resp = buildListReqForSend(search);

        Assert.assertTrue(resp.getProjectInfos().size() > 0);
        Assert.assertTrue(resp.getTotalCount()>0);
    }

    @Test
    public void listSearchProjectNo() {
        ProjectInfoSearch search = new ProjectInfoSearch();
        search.setProjectNo("A00001");

        ProjectInfoListResp resp = buildListReqForSend(search);
        List<ProjectInfo> projectInfos = resp.getProjectInfos();

        Assert.assertTrue(projectInfos.size() ==1);
        Assert.assertEquals(projectInfos.get(0).getProjectNo(),search.getProjectNo());
    }

    @Test
    public void listSearchLikeName() {
        ProjectInfoSearch search = new ProjectInfoSearch();
        search.setLikeName("hink-Lun");

        ProjectInfoListResp resp = buildListReqForSend(search);
        List<ProjectInfo> projectInfos = resp.getProjectInfos();

        Assert.assertTrue(projectInfos.size() ==1);
        Assert.assertTrue(projectInfos.get(0).getName().contains(search.getLikeName()));
    }

    @Test
    public void listSearchPort() {
        ProjectInfoSearch search = new ProjectInfoSearch();
        search.setPort(8888);

        ProjectInfoListResp resp = buildListReqForSend(search);
        List<ProjectInfo> projectInfos = resp.getProjectInfos();

        Assert.assertTrue(projectInfos.size() ==1);
        Assert.assertEquals(search.getPort(), projectInfos.get(0).getPort());
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