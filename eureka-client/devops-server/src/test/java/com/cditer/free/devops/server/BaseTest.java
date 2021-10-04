package com.cditer.free.devops.server;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chfree
 * @email chfree001@gmail.com
 * @create 2021-09-01 12:57
 * @comment
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevopsServerAppTest.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"dev","eureka-dev"})
public abstract class BaseTest {


}
