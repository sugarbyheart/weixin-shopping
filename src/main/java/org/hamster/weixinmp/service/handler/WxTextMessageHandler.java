package org.hamster.weixinmp.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgTextEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.LinkMessageService;
import org.hamster.weixinmp.service.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author subaihua.tom@gmail.com
 */

@Slf4j
@Service
public class WxTextMessageHandler implements WxMessageHandlerIfc {

    @Autowired
    private LinkMessageService linkMessageService;

    @Autowired
    private WxStorageService wxStorageService;


    private WxMsgTypeEnum[] wxMsgTypeEnums = {WxMsgTypeEnum.TEXT};

    public List<WxMsgTypeEnum> listIntetestedMessageType() {
        return Arrays.asList(wxMsgTypeEnums);
    }


    public boolean canHandle(WxBaseMsgEntity wxBaseMsgEntity) {
        for (WxMsgTypeEnum temp : Arrays.asList(wxMsgTypeEnums)) {
            if (temp.equals(WxMsgTypeEnum.inst(wxBaseMsgEntity.getMsgType()))) {
                return true;
            }
        }
        return false;
    }

    private LinkTypeEnum getLinkType(String link) {
        if (link.contains("shilladfs")) {
            return LinkTypeEnum.Xinluo;
        } else if (link.contains("lottedfs")) {
            return LinkTypeEnum.Letian;
        } else {
            return null;
        }
    }

    public WxBaseRespEntity handle(WxBaseMsgEntity wxBaseMsgEntity, Map<String, Object> context) throws WxException {

        String content = "";
        if (!canHandle(wxBaseMsgEntity)) {
            content = "无法处理这样的消息！";
        }

        WxMsgTextEntity wxMsgTextEntity = (WxMsgTextEntity) wxBaseMsgEntity;
        String link = wxMsgTextEntity.getContent();
        if (!link.startsWith("http") && !link.startsWith("https")) {
            content = "如果您想注册货品链接，请保证链接以http或者https开头";
        } else {
            LinkTypeEnum linkType = getLinkType(link);
            if (linkType == null) {
                content = "无法识别这样的链接!";
            } else {
                linkMessageService.addLink(
                        link, wxMsgTextEntity.getFromUserName(), null, linkType);
                content = "添加链接成功!";
            }
        }
        return wxStorageService.createRespText(content, wxBaseMsgEntity.getFromUserName(),
                wxBaseMsgEntity.getToUserName(), 1);
    }

    public Integer priority() {
        return 1;
    }

}
