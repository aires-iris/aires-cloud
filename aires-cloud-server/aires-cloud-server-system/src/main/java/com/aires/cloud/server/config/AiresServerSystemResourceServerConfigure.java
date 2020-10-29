package com.aires.cloud.server.config;

import com.aires.cloud.common.handler.AiresAccessDeniedHandler;
import com.aires.cloud.common.handler.AiresAuthenticationEntryPoint;
import com.aires.cloud.server.properties.ServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 所有访问aires-cloud-server-system的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问
 * @author 樊正祥
 */
@Configuration
@EnableResourceServer
public class AiresServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private AiresAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AiresAuthenticationEntryPoint exceptionEntryPoint;

    @Autowired
    private ServerSystemProperties properties;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 放行的端点列表
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
            .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }
}