package com.gx.cloud.user.admin.server.controller;


import com.gx.cloud.common.util.DateUtils;
import com.gx.cloud.common.util.UtilMap;
import com.gx.cloud.user.admin.server.constants.UserParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xun.guo
 * @since 2019-12-31
 */
@Slf4j
@RestController
@Api(value = "登录验证",tags = "登录验证")
public class LoginController {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "获取权限令牌",notes = "获取权限令牌")
    @RequestMapping(value = "/login/token",method = RequestMethod.POST)
    public OAuth2AccessToken getLoginToken(@RequestParam("username") String username,@RequestParam("password") String password) throws Exception{
        log.info("用户 "+username+" 进行登录 时间：{}", DateUtils.dateToString(new Date()));
        OAuth2AccessToken result = getToken(username,password,clientId,clientSecret);
        return result;
    }

    /**
     * 生成 oauth2 token
     * @param username
     * @param password
     * @param clientId
     * @param  clientSecret
     * @return
     */
    public OAuth2AccessToken getToken(String username, String password, String clientId,String clientSecret) throws Exception {

        ServiceInstance serviceInstance = loadBalancerClient.choose(UserParameters.User_Admin_Server_Name);
        Integer port = serviceInstance.getUri().getPort();
        log.info("port = {}",port);
        String authUrl = "/oauth/token";
        log.info("url = {}",authUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(username,password);

        //定义header
//        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
//        String httpBasic = getHttpBasic(clientId, clientSecret);
//        header.add("Authorization",httpBasic);
        //定义body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","password");
        body.add("username",username);
        body.add("password",password);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, httpHeaders);
        //String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables
        //设置restTemplate远程调用时候，对400和401不让报错，正确返回数据
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if(response.getRawStatusCode()!=400 && response.getRawStatusCode()!=401){
                    super.handleError(response);
                }
            }
        });
        String url = "http://"+UserParameters.User_Admin_Server_Name+":"+port+authUrl;
        Map exchange = restTemplate.postForObject(url, httpEntity, Map.class);

        //申请令牌信息
        OAuth2AccessToken oAuth2AccessToken = UtilMap.mapToObject(exchange,OAuth2AccessToken.class);
        return oAuth2AccessToken;
    }



    //获取httpbasic的串
    private String getHttpBasic(String clientId,String clientSecret){
        String string = clientId+":"+clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic "+new String(encode);
    }

}