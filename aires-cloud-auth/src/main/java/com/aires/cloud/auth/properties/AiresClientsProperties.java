package com.aires.cloud.auth.properties;

import lombok.Data;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 20:41
 */
@Data
public class AiresClientsProperties {
    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}