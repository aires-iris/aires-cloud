package com.aires.cloud.server.test.controller;

import com.aires.cloud.server.test.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 樊正祥
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("hello")
    public String hello(String name){
        log.info("Feign调用aires-cloud-server-system-test的/hello服务,参数:"+name);
        return this.helloService.hello(name);
    }



    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1(){
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2(){
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}