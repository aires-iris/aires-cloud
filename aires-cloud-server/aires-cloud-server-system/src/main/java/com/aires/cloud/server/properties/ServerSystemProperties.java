package com.aires.cloud.server.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-13 19:49
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:aires-cloud-server-system.properties"})
@ConfigurationProperties(prefix = "aires.server.system")
public class ServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private SwaggerProperties swagger = new SwaggerProperties();
}