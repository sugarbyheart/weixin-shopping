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

}
