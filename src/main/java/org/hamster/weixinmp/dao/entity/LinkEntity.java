package org.hamster.weixinmp.dao.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.constant.LinkType;
import org.hamster.weixinmp.constant.LinkTypeEnum;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tom on 18/4/27.
 */

@Entity
@Table(name = "link")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LinkEntity extends WxBaseEntity {

    @Column(name = "open_id", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
    private String openId;
    @Column(name = "link", length = WxConfig.COL_LINK, nullable = false)
    private String link;
    @Column(name = "link_hash", length = WxConfig.COL_LINK_HASH, nullable = false)
    private String linkHash;
    @Column(name = "create_time", nullable = false)
    private Long createTime;
    @Column(name = "expire_time")
    private Long expireTime;
    @Column(name = "is_valid", nullable = false)
    private boolean isValid = true;
    @Column(name = "type", nullable = false)
    private LinkTypeEnum type;

}
