package com.gx.cloud.user.admin.server.config.oath;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class FmHelpers {

    /**
     * 认证服务器原始方式创建AccessToken
     * @param endpoints
     * @param postParameters
     * @return
     * @throws Exception
     */
    public static OAuth2AccessToken createAccessToken(AuthorizationServerEndpointsConfiguration endpoints, Map<String,String> postParameters) throws Exception{
        Assert.notNull(postParameters.get("client_id"),"client_id not null");
        Assert.notNull(postParameters.get("client_secret"),"client_secret not null");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(postParameters.get("client_id"),postParameters.get("client_secret"), Collections.emptyList());
        ResponseEntity<OAuth2AccessToken> responseEntity = endpoints.tokenEndpoint().postAccessToken(authenticationToken,postParameters);
        return responseEntity.getBody();
    }

}
