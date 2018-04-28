package org.hamster.weixinmp.service.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by tom on 18/4/26.
 */

@Slf4j
@Service
public class LetianWebService implements WebServiceInterface {

    @Override
    public boolean validUrl(String url) {
        return true;
    }

    @Override
    public boolean canBuy(String url) {
        return true;
    }

}
