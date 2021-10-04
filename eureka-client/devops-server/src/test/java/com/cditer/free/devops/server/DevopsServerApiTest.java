package com.cditer.free.devops.server;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DevopsServerApiTest extends BaseTest{

    @Autowired
    DevopsServerApi developServerApi;

    @Test
    public void name() {
        String name = developServerApi.name();
        Assert.assertEquals("devops-server", name);
    }

    @Test
    public void ping() {
        String ping = developServerApi.ping();
        Assert.assertEquals("pong", ping);
    }

    @Test
    public void pong() {
        String ping = developServerApi.pong("ping");
        Assert.assertEquals("ping:ping", ping);
    }
}