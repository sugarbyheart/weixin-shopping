package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.service.logic.MainService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MainServiceTest extends AbstractWxServiceTest {

    @Autowired
    private MainService mainService;

    @Test
    public void testAddLink() {

        mainService.start();
    }
}
