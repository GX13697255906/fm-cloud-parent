package com.gx.cloud.admin.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"com.gx.cloud.admin.server.mapper"})
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
public class FmCloudAdminServerApplication {

    public static void main(String[] args) {
        log.info("开始加载文件管理模块");
        SpringApplication.run(FmCloudAdminServerApplication.class, args);
    }

}
