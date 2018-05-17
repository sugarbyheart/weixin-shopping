package org.hamster.weixinmp.test.web;

import org.hamster.weixinmp.test.base.AbstractWxServiceTest;
import org.junit.Test;

/**
 * Created by tom on 18/4/25.
 */
public class WebServiceTest extends AbstractWxServiceTest {

    private final String canBuyXinluo1 =
            "http://shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
    private final String cannotBuyXinluo1 =
            "http://shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";
    private final String canBuyLetian1 =
            "http://chn.lottedfs.com/kr/product/productDetail?prdNo=10002257648";
    private final String cannotBuyLetian1 =
            "http://chn.lottedfs.com/kr/product/productDetail?prdNo=20000442087";

    private final String canBuyXinluo2 =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
    private final String cannotBuyXinluo2 =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";
    private final String canBuyLetian2 =
            "http://m.chn.lottedfs.com/kr/product/productDetail?prdNo=10002257648";
    private final String cannotBuyLetian2 =
            "http://m.chn.lottedfs.com/kr/product/productDetail?prdNo=20000442087";

    private final String canBuyLetian3 = "http://chn.lottedfs.com/kr/product/productDetail?prdNo=10000041804";
    private final String cannotBuyLetian3 = "http://chn.lottedfs.com/kr/product/productDetail?prdNo=10001836395";


    @Test
    public void testLinkCheck() {
//        Assert.assertTrue(xinluoWebService.canBuy(canBuyXinluo1));
//        Assert.assertFalse(xinluoWebService.canBuy(cannotBuyXinluo1));
//        Assert.assertTrue(letianWebService.canBuy(canBuyLetian1));
//        Assert.assertFalse(letianWebService.canBuy(cannotBuyLetian1));
//        Assert.assertTrue(xinluoWebService.canBuy(canBuyXinluo2));
//        Assert.assertFalse(xinluoWebService.canBuy(cannotBuyXinluo2));
//        Assert.assertTrue(letianWebService.canBuy(canBuyLetian2));
//        Assert.assertFalse(letianWebService.canBuy(cannotBuyLetian2));
//        Assert.assertTrue(letianWebService.canBuy(canBuyLetian3));
//        Assert.assertFalse(letianWebService.canBuy(cannotBuyLetian3));
    }


}
