package org.hamster.weixinmp.service.web;

import org.hamster.weixinmp.constant.LinkTypeEnum;

/**
 * Created by tom on 18/4/26.
 */

interface WebServiceInterface {

    boolean validUrl(String url);
    boolean canBuy(String url);
    LinkTypeEnum linkType();


}
