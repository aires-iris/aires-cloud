package com.aires.cloud.server;

import com.aires.cloud.common.annotation.AiresCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-12 14:55
 */
//@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AiresCloudApplication
@EnableTransactionManagement
@MapperScan("com.aires.cloud.server.mapper")
public class ServerApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(ServerApplication.class,args);
    }
}
