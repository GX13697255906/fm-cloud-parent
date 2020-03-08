package com.gx.cloud.user.admin.server.service.impl;

import com.gx.cloud.user.admin.server.entity.FmOauthClientDetails;
import com.gx.cloud.user.admin.server.entity.OauthClientDetails;
import com.gx.cloud.user.admin.server.service.OauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        List<OauthClientDetails> list = new ArrayList<>();
        list = oauthClientDetailsService.list();
        FmOauthClientDetails fmOauthClientDetails = new FmOauthClientDetails();
        BeanUtils.copyProperties(list.get(0),fmOauthClientDetails);
        ClientDetails clientDetails = fmOauthClientDetails;
        return clientDetails;
    }
}
