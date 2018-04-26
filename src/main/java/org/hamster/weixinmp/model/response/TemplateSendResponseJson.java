package org.hamster.weixinmp.model.response;

import lombok.Data;

/**
 * Created by tom on 18/4/26.
 */

@Data
public class TemplateSendResponseJson {

    private Integer errcode;
    private String errmsg;
    private Long msgid;
}

