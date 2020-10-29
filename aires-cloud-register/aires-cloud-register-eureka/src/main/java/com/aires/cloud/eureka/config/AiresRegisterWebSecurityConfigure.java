package com.aires.cloud.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 注册中心的断点保护配置
 * @author aires
 */
@EnableWebSecurity
public class AiresRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    /**
     * 注册中心放行/eureka和/actuator 让其他服务注册和admin进行健康检查
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**")
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll();
        super.configure(http);
    }
}
