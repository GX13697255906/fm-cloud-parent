package com.gx.cloud.user.admin.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author xun.guo
 * @since 2020-03-03
 * @Description  自定义客户端信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FmOauthClientDetails implements Serializable, ClientDetails {

    public String clientId;

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    public Set<String> resourceIds;

    public boolean isSecretRequired;

    public String clientSecret;

    public boolean isScoped() {
        return false;
    }

    public Set<String> scope;

    public Set<String> authorizedGrantTypes;

    public Set<String> registeredRedirectUri;

    public Collection<GrantedAuthority> authorities;

    public Integer accessTokenValiditySeconds;

    public Integer refreshTokenValiditySeconds;

    public Map<String, Object> additionalInformation;
}
