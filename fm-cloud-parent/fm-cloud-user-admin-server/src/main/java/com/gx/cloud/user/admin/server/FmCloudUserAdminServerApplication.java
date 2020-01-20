package com.gx.cloud.user.admin.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.gx.colud.user.admin.server.mapper")
public class FmCloudUserAdminServerApplication {


    public static void main(String[] args) {
        log.info("开始加载用户管理模块");
        SpringApplication.run(FmCloudUserAdminServerApplication.class, args);
    }

}
