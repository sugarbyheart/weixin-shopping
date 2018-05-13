/**
 *
 */
package org.hamster.weixinmp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 29, 2013
 */
@Getter
@Configuration
@PropertySource("classpath:wx.properties")
public class WxConfig {

    public static final String TABLE_PREFIX = "wx_";
    public static final int COL_LEN_URL = 1024;
    public static final int COL_LEN_CONTENT = 4000;
    public static final int COL_LEN_TITLE = 200;
    public static final int COL_LEN_USER_NAME = 100;
    public static final int COL_LEN_INDICATOR = 64;
    public static final int COL_LINK = 1000;
    public static final int COL_LINK_HASH = 30;

    @Value("${wx_token}")
    private String token;
    @Value("${wx_appid}")
    private String appid;
    @Value("${wx_appsecret}")
    private String appsecret;

    @Value("${wx_menu_create_url}")
    private String menuCreateUrl;
    @Value("${wx_menu_get_url}")
    private String menuGetUrl;
    @Value("${wx_menu_delete_url}")
    private String menuDeleteUrl;

    @Value("${wx_access_token_create_url}")
    private String accessTokenCreateUrl;

    @Value("${wx_custom_send_url}")
    private String customSendUrl;

    @Value("${wx_media_upload_url}")
    private String mediaUploadUrl;

    @Value("${wx_qrcode_create_url}")
    private String qrcodeCreateUrl;

    @Value("${wx_user_info_url}")
    private String userInfoUrl;
    @Value("${wx_user_get_url}")
    private String userGetUrl;

    @Value("${wx_groups_create_url}")
    private String groupsCreateUrl;
    @Value("${wx_groups_get_url}")
    private String groupsGetUrl;
    @Value("${wx_groups_getid_url}")
    private String groupsGetIdUrl;
    @Value("${wx_groups_update_url}")
    private String groupsUpdateUrl;
    @Value("${wx_groups_members_update_url}")
    private String groupsMembersUpdateUrl;

    @Value("${wx_template_send_url}")
    private String templateSendUrl;
    @Value("${wx_default_template_id}")
    private String defaultTemplateId;


}
