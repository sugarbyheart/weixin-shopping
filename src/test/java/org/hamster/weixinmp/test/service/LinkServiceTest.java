package org.hamster.weixinmp.test.service;

import com.github.sugarbyheart.daigou.common.Enum.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.dao.repository.logic.LinkDao;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceTest {

    @Autowired
    private LinkDao userDao;

    @Autowired
    private LinkMessageService linkMessageService;


    @Test
    public void addLinks() {
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setLink("http://www.shilladfs.com/estore/kr/zh/Domestic-Brand/Skin-Care/Sun-Care/p/437728");
        linkEntity.setImageLink("http://www.shilladfs.com/estore/kr/zh/Domestic-Brand/Skin-Care/Sun-Care/p/437728");
        linkEntity.setOpenId("obPqk0wQQs5XeiTvtNTuwuIM2EPA");
        linkEntity.setCreateTime(System.currentTimeMillis());
        linkEntity.setType(LinkTypeEnum.Xinluo.toString());
        linkEntity.setValid(true);
        userDao.save(linkEntity);
    }

    @Test
    public void loadLinkEntitiesTest() {
        linkMessageService.loadLinkEntities();
    }

}
