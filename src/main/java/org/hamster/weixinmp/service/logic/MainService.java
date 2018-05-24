package org.hamster.weixinmp.service.logic;

import com.github.sugarbyheart.daigou.common.DTO.ItemDiscription;
import com.github.sugarbyheart.daigou.common.Enum.LinkTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.messaging.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by tom on 18/4/28.
 */

@Slf4j
@Component
public class MainService {

    @Autowired
    private LinkMessageService linkMessageService;

    @Autowired
    private ProducerService producerService;

    @PostConstruct
    public void init() {
        log.info("Post construct");
    }

    public MainService() {
        log.info("Finished initializing main service");
    }

    @Scheduled(fixedRate = 20000)
    public void start() {
        List<LinkEntity> linkEntityList = linkMessageService.loadLinkEntities();
        log.info("Load all the links, size:{}, timestamp:{}", linkEntityList.size(), new Date());
        for (LinkEntity linkEntity : linkEntityList) {

            ItemDiscription itemDiscription = ItemDiscription.builder()
                    .openId(linkEntity.getOpenId())
                    .link(linkEntity.getLink())
                    .linkTypeEnum(LinkTypeEnum.inst(linkEntity.getType()))
                    .build();

            producerService.sendDiscription(itemDiscription);
            log.info("Send item: " + itemDiscription.toString());
        }
    }
}
