package org.hamster.weixinmp.test.web;

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
public class WebScratchTest extends AbstractWxServiceTest {

    private final String canBuy =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
    private final String cannotBuy =
            "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";

    @Autowired
    XinluoWebService xinluoWebService;

    @Test
    public void testXinluo() {
        Assert.assertTrue(xinluoWebService.canBuy(canBuy));
        Assert.assertFalse(xinluoWebService.canBuy(cannotBuy));
//        String staffUrl = "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
//        String noStaffUrl = "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";
//        String staffSelector = "#container > div.detail_view > div.pr_wrap.clear_both > div.txt > ul > li > a.btn_soldout";
//        long start = System.currentTimeMillis();
//        try {
//            Document doc1 = Jsoup.connect(staffUrl).get();
//            Document doc2 = Jsoup.connect(noStaffUrl).get();
//            Elements elements = doc1.select(staffSelector);
//            for (int i = 0; i < elements.size(); i++) {
//                Element element = elements.get(i);
//                System.out.println(element.toString());
//            }
//
//            elements = doc2.select(staffSelector);
//            for (int i = 0; i < elements.size(); i++) {
//                Element element = elements.get(i);
//                System.out.println(element.toString());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("Time is:" + (System.currentTimeMillis() - start) + "ms");
//        }
    }


}
