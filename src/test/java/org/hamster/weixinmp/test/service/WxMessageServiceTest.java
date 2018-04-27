package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.config.WxConfig;
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


    @Test
    public void testTemplateSend() {

        try {
            TemplateSendResponseJson templateSendResponseJson =
                    wxMessageService.remoteSendTemplate(accessToken, openId, wxConfig.getDefaultTemplateId(), url);
            Assert.assertTrue(templateSendResponseJson.getErrcode().equals(0));
            Assert.assertEquals(templateSendResponseJson.getErrmsg(), "ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
