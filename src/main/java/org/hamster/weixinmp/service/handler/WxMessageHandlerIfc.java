/**
 * 
 */
package org.hamster.weixinmp.service.handler;

import java.util.List;
import java.util.Map;

import org.hamster.weixinmp.constant.WxMsgTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseRespEntity;
import org.hamster.weixinmp.exception.WxException;

/**
 * @author grossopaforever@gmail.com
 * @version Jan 5, 2014
 *
 */
public interface WxMessageHandlerIfc {
	
	List<WxMsgTypeEnum> listIntetestedMessageType();

	boolean canHandle( WxBaseMsgEntity wxBaseMsgEntity);
	
	WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context) throws WxException;
	
	Integer priority();
}
