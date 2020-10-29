package com.aires.cloud.common.config;

import com.aires.cloud.common.handler.AiresAccessDeniedHandler;
import com.aires.cloud.common.handler.AiresAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 21:04
 */
public class AiresAuthExceptionConfigure {
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public AiresAccessDeniedHandler accessDeniedHandler() {
        return new AiresAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public AiresAuthenticationEntryPoint authenticationEntryPoint() {
        return new AiresAuthenticationEntryPoint();
    }
}