package org.hamster.weixinmp.service.logic;

import com.github.sugarbyheart.daigou.common.DTO.ItemDiscription;
import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.messaging.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    @Scheduled(fixedRate = 2000)
    public void start() {
        List<LinkEntity> linkEntityList = linkMessageService.loadLinkEntities();
        for (LinkEntity linkEntity : linkEntityList) {

            ItemDiscription itemDiscription = ItemDiscription.builder()
                    .openId(linkEntity.getOpenId())
                    .link(linkEntity.getLink())
                    .linkTypeEnum(linkEntity.getLinkTypeEnum())
                    .itemBrandEnum(linkEntity.getItemBrandEnum())
                    .build();

            producerService.sendDiscription(itemDiscription);
        }
    }
}

//            try {
//                if (linkEntity == null) {
//                    continue;
//                }
//                if (linkEntity.getType().equals(LinkTypeEnum.Xinluo.toString())) {
//                    log.info("Check Xinluo link: " + linkEntity.getLink());
//                    if (xinluoWebService.canBuy(linkEntity.getLink())) {
//                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
//                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
//                    }
//                } else if (linkEntity.getType().equals(LinkTypeEnum.Letian.toString())) {
//                    log.info("Check Letian link: " + linkEntity.getLink());
//                    if (letianWebService.canBuy(linkEntity.getLink())) {
//                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
//                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
//                    }
//                } else {
//                    // Never heppen
//                    log.info("Invalid link type, Type:" + linkEntity.getType());
//                }
//            } catch (Exception e) {
//                log.info("Exception: ", e);
//            }
