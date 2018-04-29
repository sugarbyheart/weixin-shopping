package org.hamster.weixinmp.service.web;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Created by tom on 18/4/26.
 */

@Slf4j
@Service
public class LetianWebService implements WebServiceInterface {

    private final String base = "chn.lottedfs.com";
    private final String selector = "#prdDetailTopArea > div.productArea > div.info > div.buyBtn.soldOut";

    @Override
    public LinkTypeEnum linkType() {
        return LinkTypeEnum.Letian;
    }

    @Override
    public boolean validUrl(String url) {
        if (!url.contains(base)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBuy(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(selector);
            if (elements != null && elements.size() != 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception: {}", e);
            return false;
        }
    }

}
