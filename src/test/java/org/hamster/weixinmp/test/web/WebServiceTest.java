package org.hamster.weixinmp.test.web;

import org.hamster.weixinmp.service.web.LetianWebService;
import org.hamster.weixinmp.service.web.XinluoWebService;
import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tom on 18/4/25.
 */
public class WebServiceTest extends AbstractWxServiceTest {

    private final String canBuyXinluo =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
    private final String cannotBuyXinluo =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";
    private final String canBuyLetian =
            "http://chn.lottedfs.com/kr/product/productDetail?prdNo=10002257648";
    private final String cannotBuyLetian =
            "http://chn.lottedfs.com/kr/product/productDetail?prdNo=20000442087";

    @Autowired
    private XinluoWebService xinluoWebService;

    @Autowired
    private LetianWebService letianWebService;

    @Test
    public void testLinkCheck() {
        Assert.assertTrue(xinluoWebService.canBuy(canBuyXinluo));
        Assert.assertFalse(xinluoWebService.canBuy(cannotBuyXinluo));
        Assert.assertTrue(letianWebService.canBuy(canBuyLetian));
        Assert.assertFalse(letianWebService.canBuy(cannotBuyLetian));
    }


}
