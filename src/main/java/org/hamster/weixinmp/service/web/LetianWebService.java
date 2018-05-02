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
    private final String selector1 = "#prdDetailTopArea > div.productArea > div.info > div.buyBtn.soldOut";
    private final String selector2 = "#prdDetailTopArea > div.rightSection " +
            "> div.tab-container > div.tabsBody.tabs01 > div.btn > a";

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
            Elements elements1 = doc.select(selector1);
            Elements elements2 = doc.select(selector2);
            if ((elements1 != null && elements1.size() != 0)
                    || elements2 != null && elements2.size() == 2) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Exception: {}", e);
            return false;
        }
    }

}
