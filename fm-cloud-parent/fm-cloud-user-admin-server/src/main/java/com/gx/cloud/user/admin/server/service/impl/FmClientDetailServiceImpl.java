package com.gx.cloud.user.admin.server.service.impl;

import com.gx.cloud.user.admin.server.controller.LoginController;
import com.gx.cloud.user.admin.server.entity.FmOauthClientDetails;
import com.gx.cloud.user.admin.server.entity.OauthClientDetails;
import com.gx.cloud.user.admin.server.service.OauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class FmClientDetailServiceImpl implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }
}
