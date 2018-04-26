package org.hamster.weixinmp.service.web;

/**
 * Created by tom on 18/4/26.
 */

interface WebServiceInterface {

    boolean validUrl(String url);
    boolean canBuy(String url);


}
