package org.hamster.weixinmp.service.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LetianItemService {

    private String select1 = "img[onerror*=280x280]";
    private String select2 = "img[onerror*=460x460]";

    public String getImagelink(String itemLink) throws IOException {

        Document doc = Jsoup.connect(itemLink).get();

        Elements elements = doc.select(select1);

        if (elements != null && elements.size() != 0) {
            return elements.get(0).attr("src");
        }

        elements = doc.select(select2);

        if (elements != null && elements.size() != 0) {
            return elements.get(0).attr("src");
        }

        return null;

    }

}
