package com.aires.cloud.server.test;

import com.aires.cloud.common.annotation.AiresCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 14:55
 */
//@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@AiresCloudApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerTestApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(ServerTestApplication.class,args);
    }
}
