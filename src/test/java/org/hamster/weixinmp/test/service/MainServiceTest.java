package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.service.logic.MainService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MainServiceTest extends AbstractWxServiceTest {

    @Autowired
    private MainService mainService;

    @Test
    public void testAddLink() {
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setLink("http://m.shilladfs.com/estore/kr/zh/Skin-Care/" +
                "Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836");
        linkEntity.setOpenId("11111");
        linkEntity.setValid(true);
        linkEntity.setCreateTime(System.currentTimeMillis());
        linkEntity.setLinkHash("1111");
        linkEntity.setType(LinkTypeEnum.Xinluo);
        Assert.assertTrue(mainService.addLinkEntity(linkEntity));

        mainService.start();
    }
}
