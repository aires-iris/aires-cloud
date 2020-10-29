package com.aires.cloud.auth;

import com.aires.cloud.common.annotation.EnableAiresAuthExceptionHandler;
import com.aires.cloud.common.annotation.EnableAiresLettuceRedis;
import com.aires.cloud.common.annotation.EnableAiresServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author aires
 */
//@EnableEurekaClient
@SpringBootApplication
@EnableAiresServerProtect
@EnableAiresAuthExceptionHandler
@EnableAiresLettuceRedis
@MapperScan("com.aires.cloud.auth.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(AuthApplication.class,args);
    }
}
