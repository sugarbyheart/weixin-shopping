package org.hamster.weixinmp.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.service.UserService;
import org.hamster.weixinmp.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by tom on 18/4/26.
 */

@Slf4j
@Service
public class WxEventMessageHandler implements WxMessageHandlerIfc {

    @Autowired
    private UserService userService;

    @Autowired
    private WxStorageService wxStorageService;

    private WxMsgTypeEnum[] wxMsgTypeEnums = {WxMsgTypeEnum.EVENT};

    @Override
    public List<WxMsgTypeEnum> listIntetestedMessageType() {
        return Arrays.asList(wxMsgTypeEnums);
    }

    @Override
    public boolean canHandle( WxBaseMsgEntity wxBaseMsgEntity) {
        for (WxMsgTypeEnum temp : Arrays.asList(wxMsgTypeEnums)) {
            if (temp.equals(WxMsgTypeEnum.inst(wxBaseMsgEntity.getMsgType()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) {

        if (!this.canHandle(msg)) {
            return null;
        }

        WxMsgEventEntity wxMsgEventEntity = (WxMsgEventEntity) msg;

        WxRespTextEntity wxRespTextEntity =
                wxStorageService.createRespText("", msg.getFromUserName(), msg.getToUserName(), 1);

        if (wxMsgEventEntity.getEvent().equals("subscribe")) {

            userService.userSubscribe(wxMsgEventEntity);
            wxRespTextEntity.setContent("谢谢您关注公众账号！");

        } else if (wxMsgEventEntity.getEvent().equals("unsubscribe")) {

            userService.userUnsubscribe(wxMsgEventEntity);
            userService.userSubscribe(wxMsgEventEntity);
            wxRespTextEntity.setContent("您已经取消关注公众账号！");

        } else {
            log.error("event should be in {subscribe, unsubscribe}, but:" + wxMsgEventEntity.getEvent());
        }

        return wxRespTextEntity;

    }

    @Override
    public Integer priority() {
        return 0;
    }
}
