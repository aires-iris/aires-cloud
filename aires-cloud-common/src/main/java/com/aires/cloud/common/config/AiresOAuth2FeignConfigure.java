package com.aires.cloud.common.config;

import com.aires.cloud.common.constant.AiresConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @program: cloud-project
 * @description: 拦截Feign请求，手动往请求头上加入令牌,SecurityContextHolder从请求上下文中获取了OAuth2AuthenticationDetails
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:29
 */
public class AiresOAuth2FeignConfigure {
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 在feign转发前添加 Zuul Token信息
            String zuulToken = new String(Base64Utils.encode(AiresConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(AiresConstant.ZUUL_TOKEN_HEADER, zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}