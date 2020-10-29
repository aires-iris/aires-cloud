package com.aires.cloud.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 20:42
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:aires-auth.properties"})
@ConfigurationProperties(prefix = "aires.auth")
public class AiresAuthProperties {
    private AiresClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    /**
     * 免认证路径
     */
    private String anonUrl;

    /**
     * 验证码配置类
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
}