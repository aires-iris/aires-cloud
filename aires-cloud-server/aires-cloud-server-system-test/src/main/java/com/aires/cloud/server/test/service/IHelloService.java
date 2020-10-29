package com.aires.cloud.server.test.service;

import com.aires.cloud.common.entity.AiresServerConstant;
import com.aires.cloud.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * value指定远程服务的名称
 * contextId指定这个Feign Client的别名
 * fallbackFactory指定了回退方法
 */
@FeignClient(value = AiresServerConstant.AIRES_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}