package com.gx.cloud.user.admin.server.config.security;

import com.gx.cloud.common.util.UtilMap;
import com.gx.cloud.user.admin.server.service.UserService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: zzx
 * @date: 2018/10/15 16:54
 * @description: 用户认证、权限
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(SelfUserDetailsService.class);
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<String,Object> userMap = UtilMap.objectToMap(userService.getUserByUserName(username));
        //通过username查询用户
        SelfUserDetails user = new SelfUserDetails();
        user.setId(MapUtils.getString(userMap,"id"));
        user.setUsername(MapUtils.getString(userMap,"username"));
        user.setPassword(MapUtils.getString(userMap,"password"));

        if(user == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }

        Set authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户角色
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);

        log.info("用户{}验证通过",username);
        return user;
    }
}