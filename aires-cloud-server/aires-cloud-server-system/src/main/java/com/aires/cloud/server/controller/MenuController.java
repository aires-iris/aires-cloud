package com.aires.cloud.server.controller;

import com.aires.cloud.common.entity.AiresResponse;
import com.aires.cloud.common.entity.router.VueRouter;
import com.aires.cloud.common.entity.system.Menu;
import com.aires.cloud.server.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/{username}")
    public AiresResponse getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        log.info("用户:{}正在登陆",username);
        Map<String, Object> result = new HashMap<>();
        // 构建用户路由对象
        List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(username);
        // 获取用户权限信息
        Set<String> userPermissions = this.menuService.findUserPermissions(username);
        // 组装数据
        result.put("routes", userRouters);
        result.put("permissions", userPermissions);
        return new AiresResponse().data(result);
    }
}