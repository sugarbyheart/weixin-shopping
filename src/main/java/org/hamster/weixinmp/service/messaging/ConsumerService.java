package org.hamster.weixinmp.service.messaging;


import com.github.sugarbyheart.daigou.common.DTO.ItemDetectResult;
import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.service.WxAuthService;
import org.hamster.weixinmp.service.WxMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ConsumerService {

    @Autowired
    private WxAuthService wxAuthService;

    @Autowired
    private WxMessageService wxMessageService;

    @Value("${wx_default_template_id}")
    private String templateId;

    @RabbitListener
    public void receive(ItemDetectResult itemDetectResult) {
        try {
            if (itemDetectResult.isHasItem()) {
                wxMessageService.remoteSendTemplate(wxAuthService.getAccessToken(),
                        itemDetectResult.getItemDiscription().getOpenId(),
                        templateId, itemDetectResult.getItemDiscription().getLink());
            }
        } catch (Exception e) {
            log.error("Exception: {}", e);
        }
    }

}
