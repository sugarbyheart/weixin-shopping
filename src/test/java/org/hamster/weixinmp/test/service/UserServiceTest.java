package org.hamster.weixinmp.test.service;

import org.hamster.weixinmp.dao.entity.logic.UserEntity;
import org.hamster.weixinmp.dao.entity.base.WxBaseMsgEntity;
import org.hamster.weixinmp.dao.entity.msg.WxMsgEventEntity;
import org.hamster.weixinmp.dao.repository.logic.UserDao;
import org.hamster.weixinmp.service.UserService;
import org.hamster.weixinmp.service.WxMessageService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by tom on 18/4/26.
 */
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private WxMessageService wxMessageService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private static final String MSG_USER_SUBSCRIBE =
                    "<xml>" +
                    "<ToUserName>abcdefg</ToUserName>" +
                    "<FromUserName>abcdefg</FromUserName>" +
                    "<CreateTime>123456789</CreateTime>" +
                    "<MsgType>event</MsgType>" +
                    "<Event>subscribe</Event>" +
                    "</xml>";

    private static final String MSG_USER_UNSUBSCRIBE =
            "<xml>" +
                    "<ToUserName>abcdefg</ToUserName>" +
                    "<FromUserName>abcdefg</FromUserName>" +
                    "<CreateTime>123456789</CreateTime>" +
                    "<MsgType>event</MsgType>" +
                    "<Event>unsubscribe</Event>" +
                    "</xml>";

    @Test
    public void testSubscribeMessage() throws Exception {
        WxBaseMsgEntity wxBaseMsgEntity = wxMessageService.parseXML(MSG_USER_SUBSCRIBE);
        Assert.assertEquals(wxBaseMsgEntity.getMsgType(), "event");
        Assert.assertTrue(wxBaseMsgEntity.getCreateTime().equals(123456789L));
        WxMsgEventEntity wxMsgEventEntity = (WxMsgEventEntity) wxBaseMsgEntity;
        Assert.assertEquals(wxMsgEventEntity.getEvent(), "subscribe");

        UserEntity userEntity = new UserEntity();
        userEntity.setOpenId(wxMsgEventEntity.getFromUserName());
        userEntity.setFirstFollowTime(System.currentTimeMillis());
        userEntity.setUpdateTime(System.currentTimeMillis());
        userEntity.setValid(true);
        userDao.save(userEntity);
        System.out.println(userEntity.toString());
    }

    @Test
    public void testResubscribeMessage() throws Exception {

        WxBaseMsgEntity wxBaseMsgEntity = wxMessageService.parseXML(MSG_USER_SUBSCRIBE);
        WxMsgEventEntity wxMsgEventEntity = (WxMsgEventEntity) wxBaseMsgEntity;
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenId(wxMsgEventEntity.getFromUserName());
        userEntity.setFirstFollowTime(System.currentTimeMillis());
        userEntity.setUpdateTime(System.currentTimeMillis());
        userEntity.setValid(true);
        userDao.save(userEntity);

        userEntity.setUpdateTime(System.currentTimeMillis());
        userEntity.setValid(false);
        userDao.save(userEntity);
        Assert.assertFalse(userEntity.isValid());

    }

    @Test
    public void testUserServiceSubsribe() throws Exception {
        WxBaseMsgEntity wxBaseMsgEntity = wxMessageService.parseXML(MSG_USER_SUBSCRIBE);
        Assert.assertTrue(userService.userSubscribe((WxMsgEventEntity) wxBaseMsgEntity));

        wxBaseMsgEntity = wxMessageService.parseXML(MSG_USER_UNSUBSCRIBE);
        Assert.assertTrue(userService.userUnsubscribe((WxMsgEventEntity) wxBaseMsgEntity));
    }

}
