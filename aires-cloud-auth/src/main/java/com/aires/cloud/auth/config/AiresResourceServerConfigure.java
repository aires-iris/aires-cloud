package com.aires.cloud.auth.config;

import com.aires.cloud.auth.properties.AiresAuthProperties;
import com.aires.cloud.common.handler.AiresAccessDeniedHandler;
import com.aires.cloud.common.handler.AiresAuthenticationEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 * AiresSecurityConfigure对所有/oauth开头的请求拦截，WebSecurityConfigurerAdapter的order为100
 * AiresResourceServerConfigure对所有请求拦截，@EnableWebSecurity注解的order为3
 * 所以需要调节AiresResourceServerConfigure的order使其的拦截顺序小于AiresSecurityConfigure
 * @author 樊正祥
 */
@Configuration
@EnableResourceServer
public class AiresResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private AiresAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AiresAuthenticationEntryPoint exceptionEntryPoint;

    @Autowired
    private AiresAuthProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                // 放行的端口列表
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

}
