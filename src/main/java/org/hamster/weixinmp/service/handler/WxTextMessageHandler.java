package org.hamster.weixinmp.service.handler;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author subaihua.tom@gmail.com
 */
public class WxTextMessageHandler implements WxMessageHandlerIfc {

    @Autowired
    private LinkMessageService linkMessageService;

    @Autowired
    private WxStorageService wxStorageService;

    private WxMsgTypeEnum[] wxMsgTypeEnums = {WxMsgTypeEnum.TEXT};

    public List<WxMsgTypeEnum> listIntetestedMessageType() {
        return Arrays.asList(wxMsgTypeEnums);
    }

    public boolean canHandle( WxBaseMsgEntity wxBaseMsgEntity) {
        for (WxMsgTypeEnum temp : Arrays.asList(wxMsgTypeEnums)) {
            if (temp.equals(WxMsgTypeEnum.inst(wxBaseMsgEntity.getMsgType()))) {
                return true;
            }
        }
        return false;
    }

    public WxBaseRespEntity handle(WxBaseMsgEntity wxBaseMsgEntity, Map<String, Object> context) throws WxException {

        if (!canHandle(wxBaseMsgEntity)) {
            return null;
        }

        WxMsgTextEntity wxMsgTextEntity = (WxMsgTextEntity)wxBaseMsgEntity;
        String link = wxMsgTextEntity.getContent();
        if (!link.startsWith("http") && !link.startsWith("https")) {
            throw new WxException("Link is invalid:" + link);
        }
        linkMessageService.addLink(link, wxMsgTextEntity.getFromUserName(), null);
        WxRespTextEntity wxRespTextEntity = new WxRespTextEntity();
        wxRespTextEntity.setContent("添加链接成功!");

        return wxStorageService.createRespText(
                "Only test message, please ignore this.", wxBaseMsgEntity.getFromUserName(),
                wxBaseMsgEntity.getToUserName(), 1);
    }

    public Integer priority() {
        return 1;
    }

}
