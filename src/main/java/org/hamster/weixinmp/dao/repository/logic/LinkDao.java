package org.hamster.weixinmp.dao.repository.logic;

import org.hamster.weixinmp.dao.entity.logic.LinkEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tom on 18/4/27.
 */
@Repository
public interface LinkDao extends PagingAndSortingRepository<LinkEntity, Long> {

    LinkEntity findLinkEntityByOpenIdAndLink(String openId, String link);

}