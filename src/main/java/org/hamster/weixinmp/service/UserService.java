package org.hamster.weixinmp.service;

import lombok.extern.slf4j.Slf4j;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.UserEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by tom on 18/4/26.
 */

@Slf4j
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean userSubscribe(WxMsgEventEntity wxMsgEventEntity) {

        UserEntity userEntity = userDao.findUserEntityByOpenId(wxMsgEventEntity.getFromUserName());
        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setOpenId(wxMsgEventEntity.getFromUserName());
            userEntity.setFirstFollowTime(System.currentTimeMillis());
            userEntity.setUpdateTime(System.currentTimeMillis());
            userEntity.setValid(true);
            userDao.save(userEntity);
        } else {
            userEntity.setValid(true);
            userEntity.setUpdateTime(System.currentTimeMillis());
            userDao.save(userEntity);
        }
        return true;
    }

    public boolean userUnsubscribe(WxMsgEventEntity wxMsgEventEntity) {

        UserEntity userEntity = userDao.findUserEntityByOpenId(wxMsgEventEntity.getFromUserName());
        if (userEntity == null) {
            //Do nothing
            return true;
        }
        userEntity.setValid(false);
        userEntity.setUpdateTime(System.currentTimeMillis());
        userDao.save(userEntity);
        return true;
    }


}
