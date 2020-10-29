package com.aires.cloud.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: cloud-project
 * @description:
 * @author: fan zhengxiang
 * @create: 2020-10-13 20:13
 */
//@EnableEurekaClient
@EnableAdminServer
@SpringBootApplication
public class AiresMonitorAdminApplication {
    public static void main(String[] args) {
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(AiresMonitorAdminApplication.class,args);
    }
}