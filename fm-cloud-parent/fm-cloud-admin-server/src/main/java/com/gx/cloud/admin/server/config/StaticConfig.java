package com.gx.cloud.admin.server.config;

import com.gx.cloud.common.swagger.Swagger2Config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticConfig {

    static {
        Swagger2Config.groupName = "文件管理";
    }

}
