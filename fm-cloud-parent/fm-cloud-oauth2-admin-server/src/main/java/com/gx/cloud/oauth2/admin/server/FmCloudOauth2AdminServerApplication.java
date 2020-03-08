package com.gx.cloud.oauth2.admin.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer
@MapperScan(basePackages = {"com.gx.cloud.oauth2.admin.server.mapper"})
public class FmCloudOauth2AdminServerApplication {

    public static void main(String[] args) {
        log.info("加载oauth2授权模块");
        SpringApplication.run(FmCloudOauth2AdminServerApplication.class, args);
    }

}
