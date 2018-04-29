package org.hamster.weixinmp.service;

import com.google.common.base.Strings;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.hamster.weixinmp.dao.repository.logic.LinkDao;
import org.hamster.weixinmp.exception.WxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tom on 18/4/27.
 */

@Service
public class LinkMessageService {

    @Autowired
    private LinkDao linkDao;

    private boolean checkLink(String link) {
        return true;
    }

    private LinkTypeEnum getLinkType(String link) {
        if (link.contains("shilladfs")) {
            return LinkTypeEnum.Xinluo;
        } else if (link.contains("lottedfs")) {
            return LinkTypeEnum.Letian;
        } else {
            return null;
        }
    }

    public boolean addLink(String link, String openId, Long expireTime) throws WxException {
        if (!checkLink(link)) {
            throw new WxException("Link is invalid: " + link);
        }
        LinkTypeEnum linkTypeEnum = this.getLinkType(link);
        if (linkTypeEnum == null) {
            return false;
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setLink(link);
        linkEntity.setOpenId(openId);
        linkEntity.setCreateTime(System.currentTimeMillis());
        linkEntity.setExpireTime(expireTime);
        linkEntity.setValid(true);
        linkEntity.setType(linkTypeEnum);
        linkDao.save(linkEntity);
        return true;
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
            LinkEntity linkEntity = iterator.next();
            if (linkEntity.getExpireTime() == null ||
                    linkEntity.getExpireTime() >= System.currentTimeMillis()) {
                linkEntityList.add(iterator.next());
            }
        }
        return linkEntityList;
    }

}
