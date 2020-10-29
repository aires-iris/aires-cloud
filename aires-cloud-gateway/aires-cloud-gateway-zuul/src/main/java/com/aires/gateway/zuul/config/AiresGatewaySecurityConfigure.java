package com.aires.gateway.zuul.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: cloud-project
 * @description: 因为在common模块引入了spring-security
 * @author: fan zhengxiang
 * @create: 2020-10-12 14:21
 */
@EnableWebSecurity
public class AiresGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    /**
     * 放行所有的健康检查端点和获取图片验证码端点
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
