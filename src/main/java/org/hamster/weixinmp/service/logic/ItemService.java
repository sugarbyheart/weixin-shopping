package org.hamster.weixinmp.service.logic;


import com.github.sugarbyheart.daigou.common.Enum.LinkTypeEnum;
import com.github.sugarbyheart.daigou.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemService {

    @Autowired
    LetianItemService letianItemService;

    @Autowired
    XinluoItemService xinluoItemService;

    public String getImageLink(String link) {
        LinkTypeEnum linkTypeEnum = Utils.getLinkTypeEnum(link);
        try {
            switch (linkTypeEnum) {
                case Xinluo:
                    return xinluoItemService.getImagelink(link);
                case Letian:
                    return letianItemService.getImagelink(link);
                default:
                    return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

}
