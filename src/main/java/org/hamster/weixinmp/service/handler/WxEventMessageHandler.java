package org.hamster.weixinmp.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.repository.UserDao;
import org.hamster.weixinmp.service.UserService;
import org.hamster.weixinmp.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by tom on 18/4/26.
 */

@Slf4j
public class WxEventMessageHandler implements WxMessageHandlerIfc {

    @Autowired
    private UserService userService;

    private WxMsgTypeEnum[] wxMsgTypeEnums = {WxMsgTypeEnum.EVENT};

    @Override
    public WxMsgTypeEnum[] listIntetestedMessageType() {
        return wxMsgTypeEnums;
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) {

        WxMsgEventEntity wxMsgEventEntity = (WxMsgEventEntity) msg;
        if (wxMsgEventEntity.getEvent().equals("subscribe")) {

            userService.userSubscribe(wxMsgEventEntity);

        } else if (wxMsgEventEntity.getEvent().equals("unsubscribe")) {

            userService.userUnsubscribe(wxMsgEventEntity);

        } else {
            log.error("event should be in {subscribe, unsubscribe}, but:" + wxMsgEventEntity.getEvent());
        }

        return null;

    }

    @Override
    public Integer priority() {
        return 0;
    }
}
