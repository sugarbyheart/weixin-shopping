package org.hamster.weixinmp.service;

import com.google.common.base.Strings;
import org.hamster.weixinmp.dao.entity.LinkEntity;
import org.hamster.weixinmp.dao.repository.LinkDao;
import org.hamster.weixinmp.exception.WxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tom on 18/4/27.
 */

@Component
public class LinkMessageService {

    @Autowired
    private LinkDao linkDao;

    private boolean checkLink(String link) {
        return true;
    }

    public Long addLink(String link, String openId, Long expireTime) throws WxException {
        if (!checkLink(link)) {
            throw new WxException("Link is invalid: " + link);
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setLink(link);
        linkEntity.setOpenId(openId);
        linkEntity.setCreateTime(System.currentTimeMillis());
        linkEntity.setExpireTime(expireTime);
        linkEntity.setValid(true);
        linkDao.save(linkEntity);
        return linkEntity.getId();
    }

    public Long removeLink(String link, String openId) {
        if (Strings.isNullOrEmpty(link) || Strings.isNullOrEmpty(openId)) {
            throw new IllegalArgumentException("link or openId is null");
        }
        LinkEntity linkEntity = linkDao.findLinkEntityByOpenIdAndLink(openId, link);
        if (linkEntity == null) {
            return -1L;
        }
        linkEntity.setValid(false);
        linkDao.save(linkEntity);
        return linkEntity.getId();
    }

    public List<LinkEntity> loadLinkEntities() {
        List<LinkEntity> linkEntityList = new ArrayList<>();
        Iterator<LinkEntity> iterator = linkDao.findAll().iterator();
        while(iterator.hasNext()) {
            linkEntityList.add(iterator.next());
        }
        return linkEntityList;
    }

}
