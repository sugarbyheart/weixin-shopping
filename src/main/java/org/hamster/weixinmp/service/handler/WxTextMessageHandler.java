package org.hamster.weixinmp.service.handler;

import com.github.sugarbyheart.daigou.common.Enum.LinkTypeEnum;
import lombok.extern.slf4j.Slf4j;
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

    public WxBaseRespEntity handle(WxBaseMsgEntity wxBaseMsgEntity) throws WxException {

        String content = "";
        WxMsgTextEntity wxMsgTextEntity = (WxMsgTextEntity) wxBaseMsgEntity;
        String link = wxMsgTextEntity.getContent();
        if (!link.startsWith("http") && !link.startsWith("https")) {
            content = "如果您想注册货品链接，请保证链接以http或者https开头";
        } else {
            LinkTypeEnum linkType = getLinkType(link);
            if (linkType == null) {
                content = "无法识别这样的链接!我们只支持新罗、乐天的免税店网站链接; " +
                        "比如: http://www.shilladfs.com/estore/kr/zh/Makeup/Base/Primer/p/3317805";
            } else {
                if (linkMessageService.checkValidByOpenIdAndLink(wxBaseMsgEntity.getFromUserName(), link)) {
                    content = "您已经添加过此链接，并且该链接当前有效！";
                } else {
                    linkMessageService.addLink(
                            link, wxMsgTextEntity.getFromUserName(), null, linkType);
                    content = "添加链接成功!";
                }
            }
        }

        return wxStorageService.createRespText(content, wxBaseMsgEntity.getFromUserName(),
                wxBaseMsgEntity.getToUserName(), 1);
    }

    public Integer priority() {
        return 1;
    }

}
