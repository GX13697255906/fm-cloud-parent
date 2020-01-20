package com.gx.cloud.api.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gx.cloud.api.gateway.mapper")
@Slf4j
public class ApiGatewayServerApplication {

    public static void main(String[] args) {
        log.info("开始执行GateWay网关程序");
        SpringApplication.run(ApiGatewayServerApplication.class, args);
    }

}
