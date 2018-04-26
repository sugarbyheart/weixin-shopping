package org.hamster.weixinmp.test.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Created by tom on 18/4/25.
 */
public class WebScratchTest {

    public void linkTest() {

        //新罗免税店例子
        String staffUrl = "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Skin-Toner/p/408672";
        String noStaffUrl = "http://m.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Basic-Skin-Care-Set/p/3181836";
        String staffSelector = "#container > div.detail_view > div.pr_wrap.clear_both > div.txt > ul > li > a.btn_soldout";
        long start = System.currentTimeMillis();
        try {
            Document doc1 = Jsoup.connect(staffUrl).get();
            Document doc2 = Jsoup.connect(noStaffUrl).get();
            Elements elements = doc1.select(staffSelector);
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                System.out.println(element.toString());
            }

            elements = doc2.select(staffSelector);
            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                System.out.println(element.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Time is:" + (System.currentTimeMillis() - start) + "ms");
        }

    }


}
