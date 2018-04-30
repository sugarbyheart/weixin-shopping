package org.hamster.weixinmp.test.service;

import org.dom4j.DocumentException;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.model.WxRespCode;
import org.hamster.weixinmp.model.response.TemplateSendResponseJson;
import org.hamster.weixinmp.service.WxMessageService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tom on 18/4/26.
 */
public class WxMessageServiceTest extends AbstractWxServiceTest {

    @Autowired
    WxMessageService wxMessageService;

    @Autowired
    WxConfig wxConfig;

    private final String openId = "obPqk0wQQs5XeiTvtNTuwuIM2EPA";
    private final String url =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";

    private final String MSG_USER_SUBSCRIBE =
                    "<xml>" +
                    "<ToUserName>abcdefg</ToUserName>" +
                    "<FromUserName>abcdefg</FromUserName>" +
                    "<CreateTime>123456789</CreateTime>" +
                    "<MsgType>event</MsgType>" +
                    "<Event>subscribe</Event>" +
                    "</xml>";

    @Test
    public void testTemplateSend() {

        try {
            WxRespCode templateSendResponseJson =
                    wxMessageService.remoteSendTemplate(accessToken, openId, wxConfig.getDefaultTemplateId(), url);
            Assert.assertTrue(templateSendResponseJson.getErrcode().equals(0));
            Assert.assertEquals(templateSendResponseJson.getErrmsg(), "ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMesageSend() {

        try {
            WxRespCode wxRespCode = wxMessageService.remoteSendText(accessToken, openId, "Hello");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserSubscribe() throws DocumentException, WxException {
        WxBaseMsgEntity wxBaseMsgEntity = wxMessageService.parseXML(MSG_USER_SUBSCRIBE);
        Assert.assertNotNull(wxMessageService.handleMessage(wxBaseMsgEntity));
    }


}
