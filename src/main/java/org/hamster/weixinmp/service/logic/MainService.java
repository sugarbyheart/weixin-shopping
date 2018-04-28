package org.hamster.weixinmp.service.logic;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.LinkEntity;
import org.hamster.weixinmp.dao.entity.UserEntity;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.UserService;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.hamster.weixinmp.service.web.LetianWebService;
import org.hamster.weixinmp.service.web.XinluoWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by tom on 18/4/28.
 */

@Slf4j
@Service
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
    private UserService userService;

    @Autowired
    private LinkMessageService linkMessageService;

    private ConcurrentLinkedDeque<UserEntity> userEntitiyList;
    private ConcurrentLinkedDeque<LinkEntity> linkEntityList;

    public boolean addLinkEntity(LinkEntity linkEntity) {
        return linkEntityList.offer(linkEntity);
    }

    public void init() {
        for (UserEntity userEntity : userService.loadUsers()) {
            userEntitiyList.add(userEntity);
        }
        for (LinkEntity linkEntity : linkMessageService.loadLinkEntities()) {
            linkEntityList.add(linkEntity);
        }
    }

    public void start() {

        while (true) {

            LinkEntity linkEntity = linkEntityList.poll();
            try {
                if (linkEntity == null) {
                    continue;
                }
                if (linkEntity.getType().equals(LinkTypeEnum.Xinluo)) {
                    log.info("Check Xinluo link: " + linkEntity.getLink());
                    if (xinluoWebService.canBuy(linkEntity.getLink())) {
                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
                    }
                } else if (linkEntity.getType().equals(LinkTypeEnum.Letian)) {
                    log.info("Check Letian link: " + linkEntity.getLink());
                    if (letianWebService.canBuy(linkEntity.getLink())) {
                        wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
                                linkEntity.getOpenId(), wxConfig.getDefaultTemplateId(), linkEntity.getLink());
                    }
                } else {
                    // Never heppen
                }
            } catch (Exception e) {
                log.error("Exception: ", e);
                if (linkEntity != null) {
                    linkEntityList.offer(linkEntity);
                }
            }
        }

    }

}
