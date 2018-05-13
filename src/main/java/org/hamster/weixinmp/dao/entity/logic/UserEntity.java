package org.hamster.weixinmp.dao.entity.logic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hamster.weixinmp.config.WxConfig;
import org.hamster.weixinmp.dao.entity.base.WxBaseEntity;

@Entity
@Table(name = "user")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends WxBaseEntity {
    @Column(name = "open_id", length = WxConfig.COL_LEN_INDICATOR, nullable = false)
    private String openId;
    @Column(name = "first_follow_time", nullable = false)
    private Long firstFollowTime;
    @Column(name = "update_time", nullable = false)
    private Long updateTime;
    @Column(name = "is_valid", nullable = false)
    private boolean isValid = true;
}
