package org.hamster.weixinmp.service.logic;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.dao.entity.logic.UserEntity;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.UserService;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.hamster.weixinmp.service.web.LetianWebService;
import org.hamster.weixinmp.service.web.XinluoWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by tom on 18/4/28.
 */

@Slf4j
@Component
public class MainService {

    @Autowired
    private XinluoWebService xinluoWebService;
    @Autowired
    private LetianWebService letianWebService;
    @Autowired
    private WxAuthService wxAuthService;
    @Autowired
    private WxConfig wxConfig;
    @Autowired
    private WxMessageService wxMessageService;

    @Autowired
    private LinkMessageService linkMessageService;

    private ConcurrentLinkedDeque<UserEntity> userEntitiyList;
    private ConcurrentLinkedDeque<LinkEntity> linkEntityList;

    public boolean addLinkEntity(LinkEntity linkEntity) {
        return linkEntityList.offer(linkEntity);
    }

//    @PostConstruct
//    public void init() {
//        userEntitiyList = new ConcurrentLinkedDeque<>();
//        linkEntityList = new ConcurrentLinkedDeque<>();
//        for (UserEntity userEntity : userService.loadUsers()) {
//            userEntitiyList.add(userEntity);
//        }
//        for (LinkEntity linkEntity : linkMessageService.loadLinkEntities()) {
//            linkEntityList.add(linkEntity);
//        }
//    }

    @PostConstruct
    public void init() {
        log.info("Post construct");
    }

    public MainService() {
        log.info("Finished initializing main service");
    }


    @Scheduled(fixedRate = 2000)
    public void start() {
        log.info("------------------- main service start() --------------------");
        List<LinkEntity> linkEntityList = linkMessageService.loadLinkEntities();
        log.info("Link size:" + linkEntityList.size());
        for (LinkEntity linkEntity : linkEntityList) {
            try {
                if (linkEntity == null) {
                    continue;
                }
                if (linkEntity.getType().equals(LinkTypeEnum.Xinluo.toString())) {
                    log.info("Check Xinluo link: " + linkEntity.getLink());
                    if (xinluoWebService.canBuy(linkEntity.getLink())) {
                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
                    }
                } else if (linkEntity.getType().equals(LinkTypeEnum.Letian.toString())) {
                    log.info("Check Letian link: " + linkEntity.getLink());
                    if (letianWebService.canBuy(linkEntity.getLink())) {
                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
                    }
                } else {
                    // Never heppen
                    log.info("Invalid link type, Type:" + linkEntity.getType());
                }
            } catch (Exception e) {
                log.info("Exception: ", e);
            }
        }
    }

}
