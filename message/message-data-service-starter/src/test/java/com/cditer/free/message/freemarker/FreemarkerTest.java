package com.cditer.free.message.freemarker;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.cditer.free.message.testmodel.TestUser;
import org.junit.Test;

public class FreemarkerTest {

    @Test
    public void test01(){

        TestUser testUser = new TestUser();
        testUser.setName("CH");

        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());

        //假设我们引入的是Beetl引擎，则：
        Template template = engine.getTemplate("Hello ${entity.name}");
        String entity = template.render(Dict.create().set("entity", testUser));

        System.out.println(entity);
    }
}
