package com.aires.cloud.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 樊正祥
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "aires-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("/hello服务被调用,参数:"+name);
        return "hello" + name;
    }
}