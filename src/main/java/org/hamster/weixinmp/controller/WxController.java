/**
 * 
 */
package org.hamster.weixinmp.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.dao.entity.resp.WxRespTextEntity;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */

@Controller
@RequestMapping("/api/v1")
@Slf4j
public class WxController {

	@Autowired
	private WxAuthService authService;
	@Autowired
	private WxMessageService wxMessageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String authGet(@RequestParam("signature") String signature,
				   @RequestParam("timestamp") String timestamp,
				   @RequestParam("nonce") String nonce,
				   @RequestParam("echostr") String echostr) throws WxException {

		if (authService.validateAuth(signature, timestamp, nonce, echostr)) {
			log.info("received authentication message from Weixin Server.");
			return echostr;
		}

		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String post(@RequestBody String requestBody) throws DocumentException, WxException, IOException {

		WxBaseMsgEntity msg = wxMessageService.parseXML(requestBody);
		log.info("received " + msg.getMsgType() + " message.");
		WxBaseRespEntity resp = wxMessageService.handleMessage(msg);
		if (resp != null && resp.getMsgType().equals("text")) {
			WxRespTextEntity wxRespTextEntity = (WxRespTextEntity) resp;
			wxMessageService.remoteSendText(
					authService.getAccessToken(), resp.getToUserName(), wxRespTextEntity.getContent());
		}
		return wxMessageService.parseRespXML(resp).asXML();
	}

}