package org.hamster.weixinmp.model.send;

import lombok.Builder;
import lombok.Data;

/**
 * Created by tom on 18/4/26.
 */

@Data
@Builder
public class SendTemplateJson {

    //TODO
    String touser;
    String template_id;
    String url;

}
