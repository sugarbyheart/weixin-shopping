package org.hamster.weixinmp.service.web;

/**
 * Created by tom on 18/4/26.
 */
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
