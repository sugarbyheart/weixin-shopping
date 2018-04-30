/**
 * 
 */
package org.hamster.weixinmp.model.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hamster.weixinmp.model.send.item.SendItemTextJson;

/**
 * @author grossopaforever@gmail.com
 * @version Dec 30, 2013
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendTextJson {
	private String touser;
	private String msgtype;
	private SendItemTextJson text;
}
