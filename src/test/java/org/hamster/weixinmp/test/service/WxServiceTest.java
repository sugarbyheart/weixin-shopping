/**
 * 
 */
package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.dao.repository.msg.WxBaseMsgDao;
import org.hamster.weixinmp.exception.WxException;
import org.hamster.weixinmp.service.WxAuthService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxServiceTest {

	@Autowired
	private WxAuthService authService;

	@Test
	public void testValidateAuth() throws WxException {
		Assert.assertTrue(authService.validateAuth(
				"9d31490b4386ad3bb9bbb8ac5150fb3e6230c171",
				"1375112572", "1375102247",
				"5906019193781128573"));
	}
	
	public static final String MSG_LOC_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1351776360</CreateTime>"
			+ "<MsgType><![CDATA[location]]></MsgType>"
			+ "<Location_X>23.134521</Location_X>"
			+ "<Location_Y>113.358803</Location_Y>" 
			+ "<Scale>20</Scale>"
			+ "<Label><![CDATA[位置信息]]></Label>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml> ";
	
//	@Test
//	public void testMessageStorage() throws WxException, DocumentException {
//		wxService.saveMsgLoc(WxXmlUtil.toXML(MSG_LOC_XML));
//		Iterable<WxBaseMsgEntity> msgs = wxBaseMsgDao.findAll();
//		for (WxBaseMsgEntity msg : msgs) {
//			System.out.println(msg.toString());
//		}
//	}
//	
//	@Test
//	public void testCreateRespText() throws DocumentException {
//		WxRespTextEntity respText = wxService.createRespText("this is a content", "foo", "bar", 0);
//		WxXmlUtil.getRespTextXML(respText);
//	}
	

}
