package org.hamster.weixinmp.dao.repository;

import org.hamster.weixinmp.dao.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tom on 18/4/26.
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findUserEntityByOpenId(String openId);

}