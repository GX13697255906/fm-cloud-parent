package com.gx.cloud.consul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class FmCloudConsulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmCloudConsulServerApplication.class, args);
    }

}
