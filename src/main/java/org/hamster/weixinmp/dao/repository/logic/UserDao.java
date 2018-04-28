package org.hamster.weixinmp.dao.repository.logic;

import org.hamster.weixinmp.dao.entity.logic.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tom on 18/4/26.
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findUserEntityByOpenId(String openId);

}