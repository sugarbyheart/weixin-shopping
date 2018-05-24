package org.hamster.weixinmp.service.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class XinluoItemService {

    private String select_470X = "img[onerror*=NO_Image_470X]";
    private String select_320X = "img[onerror*=NO_Image_320X]";

    public String getImagelink(String itemLink) throws IOException {

        Document doc = Jsoup.connect(itemLink).get();

        Elements elements = doc.select(select_470X);

        if (elements != null && elements.size() != 0) {
            return elements.get(0).attr("src");
        }

        elements = doc.select(select_320X);

        if (elements != null && elements.size() != 0) {
            return elements.get(0).attr("src");
        }

        return null;

    }

    public static void main(String[] args) throws Exception {

        String link1 = "http://www.shilladfs.com/estore/kr/zh/Skin-Care/Basic-Skin-Care/Cream/p/372829";
            String link2 = "http://www.shilladfs.com/estore/kr/zh/Estee-Lauder/Esteelauder-Desktop/Skincare/" +
                    "Byskintrouble/Variousaging/p/361402";
        XinluoItemService itemService = new XinluoItemService();
        System.out.println(itemService.getImagelink(link1));
        System.out.println(itemService.getImagelink(link2));

    }

}
