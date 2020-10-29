package com.aires.gateway.zuul.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Aires
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:aires-gateway.properties"})
@ConfigurationProperties(prefix = "aires.gateway")
public class AiresGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
