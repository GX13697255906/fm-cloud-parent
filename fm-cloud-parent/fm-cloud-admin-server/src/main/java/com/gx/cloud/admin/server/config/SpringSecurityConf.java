package com.gx.cloud.admin.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author: xun.guo
 * @date: 2018/10/15 16:47
 * @description:
 */
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()
                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/user/registery/**",
                        "/user/login/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**","/webjars/**",
                        "/v2/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/doc.html")
                .permitAll()

                .anyRequest()//任何请求,登录后可以访问
                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证

                .and()
                .formLogin()  //开启登录, 定义当需要用户登录时候，转到的登录页面
//                .loginPage("/test/login.html")
//                .loginProcessingUrl("/login")
                .permitAll()

                .and()
                .logout()//默认注销行为为logout
                .logoutUrl("/logout")
                .permitAll();

    }
}
