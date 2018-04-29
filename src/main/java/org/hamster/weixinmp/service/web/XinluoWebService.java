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
public class XinluoWebService implements WebServiceInterface {

    private final String prefix = "m.shilladfs.com";
    private final String selector =
            "#container > div.detail_view > div.pr_wrap.clear_both > div.txt > ul > li > a.btn_soldout";
//    private final String selector2 = "#reapply";

    @Override
    public LinkTypeEnum linkType() {
        return LinkTypeEnum.Xinluo;
    }

    @Override
    public boolean validUrl(String url) {
        if (!url.contains(prefix)) {
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
