package com.gx.cloud.api.gateway.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author: xun.guo
 * @date: 2018/10/15 16:47
 * @description:
 */
@EnableWebFluxSecurity
public class SpringSecurityConf{

//    @Autowired
//    AjaxAuthenticationEntryPoint authenticationEntryPoint;//未登陆时返回 JSON 格式的数据给前端（否则为 html）
//
//    @Autowired
//    AjaxAuthenticationSuccessHandler authenticationSuccessHandler; //登录成功返回的 JSON 格式数据给前端（否则为 html）
//
//    @Autowired
//    AjaxAuthenticationFailureHandler authenticationFailureHandler; //登录失败返回的 JSON 格式数据给前端（否则为 html）
//
//    @Autowired
//    AjaxLogoutSuccessHandler logoutSuccessHandler;//注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
//
//    @Autowired
//    AjaxAccessDeniedHandler accessDeniedHandler;//无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
//
//    @Autowired
//    SelfUserDetailsService userDetailsService; // 自定义user
//
//    @Autowired
//    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; // JWT 拦截器

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 加入自定义的安全认证
////        auth.authenticationProvider(provider);
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers("/**").permitAll()  //无需进行权限过滤的请求路径
//                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/user/login")
              //  .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
              //  .authenticationFailureHandler(authenticationFaillHandler) //登陆验证失败
              //  .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and() .csrf().disable()//必须支持跨域
                .logout().disable();

        return http.build();
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 去掉 CSRF
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
//                .and()
//
//                //.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
//
//                //.and()
//                .authorizeRequests()//定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/user/registery/**",
//                        "/user/login/**",
//                        "/swagger-ui.html",
//                        "/swagger-resources/**",
//                        "/images/**","/webjars/**",
//                        "/v2/api-docs",
//                        "/configuration/ui",
//                        "/configuration/security",
//                        "/doc.html")
//                .permitAll()
//
//                .anyRequest()//任何请求,登录后可以访问
//                .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
//
//                .and()
//                .formLogin()  //开启登录, 定义当需要用户登录时候，转到的登录页面
////                .loginPage("/test/login.html")
////                .loginProcessingUrl("/login")
////                .successHandler(authenticationSuccessHandler) // 登录成功
////                .failureHandler(authenticationFailureHandler) // 登录失败
//                .permitAll()
//
//                .and()
//                .logout()//默认注销行为为logout
//                .logoutUrl("/logout")
//               // .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll();

        // 记住我
//        http.rememberMe().rememberMeParameter("remember-me")
//                .userDetailsService(userDetailsService).tokenValiditySeconds(1000);
//
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

//    }
}
